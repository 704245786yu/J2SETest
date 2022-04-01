package aio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**异步的时间服务器处理类
 * @author zhiyu
 * @Date 2019-11-12
 */
public class AsyncTimeServerHandler implements Runnable {
    private int port;
    CountDownLatch countDownLatch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port){
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind( new InetSocketAddress(port) );
            System.out.println("The Time Server is start in port: "+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //CountDownLatch的作用是在完成一组正在执行的操作之前，允许当前的线程一直阻塞
        //此处让线程在此阻塞，防止服务端执行完成退出。
        countDownLatch = new CountDownLatch(1);
        doAccept();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**接收客户端连接*/
    public void doAccept(){
//        asynchronousServerSocketChannel.accept(this, new Acce);
    }
}
