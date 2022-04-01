package socket.backlog;

import java.io.IOException;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        for (int i=0; i<100; i++){
            Socket socket = new Socket("localhost", 8888);
            socket.close();
            System.out.println("客户端连接数："+(i+1));
        }
    }
}
