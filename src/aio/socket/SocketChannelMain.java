package aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhiyu
 * @Date 2019-11-18
 */
public class SocketChannelMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        /**连接成功或失败后会调用CompletionHandler*/
        socketChannel.connect(new InetSocketAddress("localhost", 8088), null, new CompletionHandler<Void, Void>() {
            @Override
            public void completed(Void result, Void attachment) {
                Future<Integer> writeFuture = socketChannel.write(ByteBuffer.wrap("我来自客户端2".getBytes()));
                try {
                    System.out.println("写入大小："+writeFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
            }
        });
        Thread.sleep(1000);
    }
}
