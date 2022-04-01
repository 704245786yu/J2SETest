package aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhiyu
 * @Date 2019-11-18
 */
public class ServerSocketMain2 {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousServerSocketChannel serverSocketChannel =
            AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8088));
        long now = System.currentTimeMillis();
        System.out.println("A "+(System.currentTimeMillis()-now));
        Future<AsynchronousSocketChannel> socketChannelFuture = serverSocketChannel.accept();
        System.out.println("B "+(System.currentTimeMillis()-now));

        AsynchronousSocketChannel socketChannel = socketChannelFuture.get();//阻塞
        System.out.println("C "+(System.currentTimeMillis()-now));

        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        System.out.println("D "+(System.currentTimeMillis()-now));
        Future<Integer> readFuture = socketChannel.read(byteBuffer);
        System.out.println("E "+(System.currentTimeMillis()-now));
        System.out.println(new String(byteBuffer.array(), 0, readFuture.get()));//阻塞
        System.out.println("F "+(System.currentTimeMillis()-now));
        Thread.sleep(40000);
    }
}
