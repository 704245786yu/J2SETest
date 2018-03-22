package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
	
	public static void main(String[] args) {
		
		//SocketAddress指示要连接的主机和端口
		SocketAddress address = new InetSocketAddress("192.168.100.104", 8080);
		try {
			//通道以阻塞模式打开，所以在这行之后的代码在真正建立连接之前不会执行
			System.out.println("建立连接中...");
			SocketChannel client = SocketChannel.open(address);
			System.out.println("连接成功");
			
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);
			
			while(client.read(buffer) != -1) {
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
