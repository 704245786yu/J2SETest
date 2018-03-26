package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**演示一个非阻塞的chargen服务器*/
public class ChargenServer {

	public static void main(String[] args) {
		byte[] rotation = new byte[95*2];
		for(byte i=' '; i<='~'; i++) {
			rotation[i-' '] = i;
			rotation[i+95-' '] = i;
		}
		
		ServerSocketChannel serverChannel;
		Selector selector;
		try {
			serverChannel = ServerSocketChannel.open();
			
			/*通过ServerSocket将ServerSocketChannel绑定到一个端口
			以下代码段将通道绑定到端口19的服务器Socket
			ServerSocket serverSocket = serverChannel.socket();
			InetSocketAddress address = new InetSocketAddress(19);
			serverSocket.bind(address);
			*/
			//在JDK 1.7及以后版本中，可以直接绑定而不用获取底层ServerSocket
			serverChannel.bind(new InetSocketAddress(19));
			
			serverChannel.configureBlocking(false);
			selector = Selector.open();
			//通过register()向监视这个通道的选择器进行注册，并指定所关注的操作
			//对于ServerSocket唯一关心的操作就是OP_ACCEPT
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		}catch(IOException ex) {
			ex.printStackTrace();
			return;
		}
		
		while(true) {
			try {
				//检查是否有可操作的数据，一般放在无限循环中，如果没有通道就绪，选择器就会等待，即此处会发生阻塞
				System.out.println("检查是否有可操作的通道...");
				selector.select();
				System.out.println("有可操作的通道!");
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			
			//selectedKeys()返回上面select()找到的就绪通道，就绪通道被包含在SelectionKey里
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while(iterator.hasNext()) {
				SelectionKey key = iterator.next();
				//从集合中删除这个键，从而不会处理两次
				iterator.remove();
				try {
					if(key.isAcceptable()) {
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
						//接受连接
						SocketChannel clientSocketChannel = serverSocketChannel.accept();
						System.out.println("Accepted connetion from "+clientSocketChannel);
						//设置客户端通道为非阻塞模式
						clientSocketChannel.configureBlocking(false);
						//对于客户端通道，需要监视是否已经准备好数据可以写入通道
						SelectionKey keyTemp = clientSocketChannel.register(selector, SelectionKey.OP_WRITE);
						//为客户端建立缓冲区
						ByteBuffer buffer = ByteBuffer.allocate(74);
						buffer.put(rotation, 0, 72);
						buffer.put((byte)'\r');
						buffer.put((byte)'\n');
						buffer.flip();
						//将通道要写入网络的缓冲区存储在SelectionKey的附件中
						keyTemp.attach(buffer);
					}else if(key.isWritable()) {
						SocketChannel client = (SocketChannel)key.channel();
						//获取键的附件
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						if(!buffer.hasRemaining()) {//检查缓冲区中是否还剩余未写的数据
							//用下一行数据重新填充缓冲区，确定最后一行从哪里开始
							buffer.rewind();
							//读取上一次的首字符
							int first = buffer.get();
							//递增到下一个准备改变缓冲区中的数据
							buffer.rewind();
							//寻找rotation中新的首字符位置
							int position = first - ' '+1;
							//将数据从rotation复制到缓冲区
							buffer.put(rotation,position, 72);
							//在缓冲区末尾存储一个行分隔符
							buffer.put((byte)'\r');
							buffer.put((byte)'\n');
							//准备缓冲区进行写入
							buffer.flip();
						}
						client.write(buffer);
					}
				} catch (IOException e) {//当客户端Socket中断时会抛出异常
					//如果结束使用连接，就要撤销其SelectionKey对象的注册，这样选择器就不会浪费资源再去查询它是否准备就绪
					//如果关闭通道，会自动在所有选择器中撤销对应这个通道的所有键的注册
					key.cancel();
					try {//关闭对应的通道
						key.channel().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		}
	}
}
