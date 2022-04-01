package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**演示基于通道的阻塞式的客户端*/
public class ChargenClient {
	
	public static void main(String[] args) {
		try {
			System.out.println("开始连接远程服务");
			//通道以【阻塞模式】打开，在连接完成之前程序会在这里阻塞
			SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 19));
			System.out.println("连接成功");
			
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel outChannel = Channels.newChannel(System.out);	//将System.out封装在一个通道中
			
            //read()返回成功读取并存储在缓冲区的字节数。返回-1表示数据结束。
			while(socketChannel.read(buffer) != -1) {
				buffer.flip();	//flip回绕缓冲区，使得输出通道会从所读取数据的开头写入
				//将读取的数据写入与System.out连接的输出通道中
				outChannel.write(buffer); //由于此处是阻塞的，所以channel会保证写入buffer里的所有字节才返回。
				buffer.clear();	//把缓冲区重置回初始状态（注意：老数据仍然存在，并没有被覆盖）
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
