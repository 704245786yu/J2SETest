package nio.channel.demo1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {
    /**以阻塞模式进行连接操作*/
    @Test
    public void testBlockingModeConnect() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        long begin = System.currentTimeMillis();
        boolean connectResult = socketChannel.connect(new InetSocketAddress("localhost", 6666));
        long end = System.currentTimeMillis();
        System.out.println("连接耗时="+(end-begin)+" connect result="+connectResult);
        socketChannel.close();
    }

    /**以非阻塞模式进行连接操作*/
    @Test
    public void testNonBlockingModeConnect() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        long begin = System.currentTimeMillis();
        boolean connectResult = socketChannel.connect(new InetSocketAddress("localhost", 6666));
        long end = System.currentTimeMillis();
        System.out.println("连接耗时="+(end-begin)+" connect result="+connectResult);
        Thread.sleep(1000);
        socketChannel.close();
    }

    /**阻塞模式。演示连接到一个不存在的ip，一定连接失败的情况*/
    @Test
    public void testIsConnectionPendingInBlocking(){
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            Assertions.assertEquals(false, socketChannel.isConnectionPending());
            socketChannel.connect(new InetSocketAddress("192.168.3.3",6666));
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.assertEquals(false, socketChannel.isConnectionPending());
        }
    }

    /**阻塞模式。演示连接到一个存在的ip，连接成功的情况*/
    @Test
    public void testIsConnectionPendingInBlocking2(){
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            Assertions.assertEquals(false, socketChannel.isConnectionPending());
            socketChannel.connect(new InetSocketAddress(6666));
            Assertions.assertEquals(false, socketChannel.isConnectionPending());
            Assertions.assertEquals(true, socketChannel.isConnected());
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.assertEquals(false, socketChannel.isConnectionPending());
        }
    }

    /**非阻塞模式*/
    @Test
    public void testIsConnectionPendingInNonBlocking(){
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Assertions.assertEquals(false, socketChannel.isConnectionPending());
            socketChannel.connect(new InetSocketAddress("192.168.3.3",6666));
            Assertions.assertEquals(true, socketChannel.isConnectionPending());
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(socketChannel.isConnectionPending());
        }
    }

    /**非阻塞模式*/
    @Test
    public void testIsConnectionPendingInNonBlocking2(){
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(6666));
            Assertions.assertEquals(true, socketChannel.isConnectionPending());

            Thread.sleep(3000);
            //这里可知，即便后期已经连接上了，非阻塞模式瞎connect()也不会去修改连接的状态。
            Assertions.assertEquals(true, socketChannel.isConnectionPending());
            Assertions.assertEquals(false, socketChannel.isConnected());
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(socketChannel.isConnectionPending());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**非阻塞模式下，finishConnect()才会更改连接状态*/
    @Test
    public void testFinishConnect() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //connect()的返回值如果为true，表示连接建立。如果通道处于非阻塞模式切正在连接中，则为false。
        boolean connect = socketChannel.connect(new InetSocketAddress(6666));
        Assertions.assertEquals(false, connect);

        Thread.sleep(1000);
        System.out.println("isConnected="+socketChannel.isConnected()+", isConnectionPending="+socketChannel.isConnectionPending());
        if(connect==false){
            while(socketChannel.finishConnect()==false){
                System.out.println("isConnected="+socketChannel.isConnected()+", isConnectionPending="+socketChannel.isConnectionPending());
                System.out.println("尝试完成连接");
            }
            System.out.println("isConnected="+socketChannel.isConnected()+", isConnectionPending="+socketChannel.isConnectionPending());
        }
        socketChannel.close();
    }
}
