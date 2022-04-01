package aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhiyu
 * @Date 2019-11-17
 */
public class ServerSocketMain {
    public static void main(String[] args) throws IOException {
        //新创建的AsynchronousServerSocketChannel已打开但尚未绑定
        AsynchronousServerSocketChannel asynServerSocketChannel =
            AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8088));
        /*accpet(attachment, handler)方法启动异步操作以接受对该通道的套接字进行的连接。
        * 参数handler是一个完成处理程序，在连接被接受时被调用（或者操作失败）
        * CompletionHandler V：IO操作的结果类型。A：附加到IO操作的对象的类型。
        */
        asynServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
                try {
//                asynServerSocketChannel.accept(null, this);
                System.out.println("public void completed ThreadName="+ Thread.currentThread().getName());
                System.out.println("服务器与" + socketChannel.getRemoteAddress() + "建立连接");

                ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                Future<Integer> readFuture = socketChannel.read(byteBuffer);

                    System.out.println(new String(byteBuffer.array(), 0, readFuture.get()));
                } catch (InterruptedException | ExecutionException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("public void failed");
            }
        });
        while(true);
    }
}
