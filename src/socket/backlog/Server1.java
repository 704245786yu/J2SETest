package socket.backlog;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class Server1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println(serverSocketChannel.isBlocking());//true
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888), 10);
        ServerSocket serverSocket = serverSocketChannel.socket();
        Thread.sleep(5000);
        boolean isRun = true;
        while(isRun){
            Socket socket = serverSocket.accept();
            Thread.sleep(1000);
//            socket.close();
            System.out.println("接收连接");
        }
        Thread.sleep(8000);
        serverSocket.close();
        serverSocketChannel.close();
    }
}
