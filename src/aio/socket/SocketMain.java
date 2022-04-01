package aio.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author zhiyu
 * @Date 2019-11-18
 */
public class SocketMain {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8088);
        OutputStream out = socket.getOutputStream();
        out.write("我来自客户端1".getBytes());
        out.flush();
        out.close();
    }
}
