package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**演示一个基于通道的chargen客户端*/
public class ChargenClient {
	
	public static void main(String[] args) {
		
		//SocketAddress指示要连接的主机和端口
		SocketAddress address = new InetSocketAddress("192.168.100.104", 19);
		try {
			System.out.println("建立连接中...");
			//通道以【阻塞模式】打开，所以在这行之后的代码在真正建立连接之前不会执行
			SocketChannel clientSocketChannel = SocketChannel.open(address);
			System.out.println("连接成功");
			
			ByteBuffer buffer = ByteBuffer.allocate(74);	//创建容量为74字节的ByteBuffer
			WritableByteChannel outChannel = Channels.newChannel(System.out);	//将System.out封装在一个通道中
			
			//将ByteBuffer对象传递给通道的read()方法，通道会用从Socket读取的数据填充这个缓冲区，它返回成功读取并存储在缓冲区的字节数。
			//返回-1表示数据结束。
			while(clientSocketChannel.read(buffer) != -1) {
				buffer.flip();	//flip回绕缓冲区，使得输出通道会从所读取数据的开头写入
				//将读取的数据写入与System.out连接的输出通道中
				//不必告诉输出通道要写入多少字节，缓冲区会记住其中包含多少字节
				outChannel.write(buffer);
				buffer.clear();	//把缓冲区重置回初始状态（注意：老数据仍然存在，并没有被覆盖）
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
