package socket.demo1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest1 {

    @Test
    public void test1(){
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("开始accept()...");
            Socket accept = serverSocket.accept();
            System.out.println("accept()返回");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
