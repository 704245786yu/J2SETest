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

/**演示在一个线程中处理多个连接（包括ServerSocketChannel）的非阻塞的chargen服务器*/
public class ChargenServer {

	public static void main(String[] args) {
		byte[] rotation = new byte[95*2];
		for(byte i=' '; i<='~'; i++) {
			rotation[i-' '] = i;
			rotation[i+95-' '] = i;
		}
		
		Selector selector;
		try {
		    //open()作用是打开ServerSocketChannel，新Channel的Socket是未绑定的。
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			/*JDK1.7之前需通过 ServerSocketChannel.socket()获取其对应的ServerSocket对象，然后将该ServerSocket绑定到指定端口：
			ServerSocket serverSocket = serverSocketChannel.socket();
			serverSocket.bind(new InetSocketAddress(19));
			而JDK 1.7+中，可以直接绑定而不用获取底层ServerSocket：
			bind(SocketAddress local, int backlog)的作用是将Channel的Socket绑定到本地地址并侦听连接。backlog用来指定挂起的最大连接数。
			*/
			serverSocketChannel.bind(new InetSocketAddress(19));
			/*要在调用ServerSocketChannel#accept()之前设置ServerSocketChannel为非阻塞模式
			* 在将Channel注册到Selector之前，必须将Channel设置成非阻塞模式，否则会报IllegalBlockingModeException异常。
			* */
			serverSocketChannel.configureBlocking(false);

			//Selector用于迭代处理所有准备好的连接
			selector = Selector.open();//创建一个新的Selector
			/*使用SelectableChannel的register()向指定的Selector进行注册此Channel，并指定该Channel所关注的操作。
			对于ServerSocket，唯一关心的操作就是OP_ACCEPT，也就是ServerSocketChannel是否准备好接受一个新连接。*/
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		}catch(IOException ex) {
			ex.printStackTrace();
			return;
		}
		
		while(true) {
			try {
				//检查是否有可操作的数据，一般放在无限循环中，如果没有通道就绪，选择器就会等待，即此处会发生阻塞
				System.out.println("检查是否有可操作的通道...");
				selector.select();//阻塞操作
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
				//通过从集合中删除键，这就告诉选择器这个键已经处理过，这样Selector就不需要在每次调用select()时再将这个键返回给我们了。从而不会处理两次。
				iterator.remove();
				try {
					if(key.isAcceptable()) {
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
						//接受连接。默认情况下accept()会阻塞，直到有一个入站连接为止。
						SocketChannel clientSocketChannel = serverSocketChannel.accept();
						System.out.println("Accepted connection from "+clientSocketChannel);
						//将接收到的客户端通道设为非阻塞模式
						clientSocketChannel.configureBlocking(false);
						//对于客户端通道，需要监视是否已经准备好数据可以写入通道
						SelectionKey keyTemp = clientSocketChannel.register(selector, SelectionKey.OP_WRITE);
						//为客户端建立缓冲区
						ByteBuffer buffer = ByteBuffer.allocate(74);
						buffer.put(rotation, 0, 72);
						buffer.put((byte)'\r');
						buffer.put((byte)'\n');
						buffer.flip();
						//将通道要写入的缓冲区存储在SelectionKey的附件中
						keyTemp.attach(buffer);
					}else if(key.isWritable()) {
						SocketChannel clientChannel = (SocketChannel)key.channel();
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
						clientChannel.write(buffer);
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
