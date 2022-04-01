package aio.asynFileChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**使用异步IO API读取文件的其中一种形式是Future模式。
 * 摘自《Java程序员修炼之道》
 * @author zhiyu
 * @Date 2019-11-14
 */
public class FutureMode {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("e:/hello.groovy");
        /*异步打开文件。
        * AsynchronousFileChannel 会关联线程池，它的任务是接收IO处理时间，并分发给负责处理通道中IO操作结果的结果处理器。
        * 如果在创建该对象时没有为其指明线程池，那就会为其分配一个系统默认的线程池（可能会和其他通道共享）。
        * 默认线程池是由AsynchronousChannelGroup类定义的系统属性进行配置的。
        */
        AsynchronousFileChannel asynFileChannel = AsynchronousFileChannel.open(path);

        ByteBuffer byteBuffer = ByteBuffer.allocate(100_000);
        //读取100,000字节。此处的IO处理能跟发起它的线程并发执行。
        //JAVA为执行这个任务创建了线程池和通道组。你也可以自己提供和配置一个。
        Future<Integer> future = asynFileChannel.read(byteBuffer, 0);

        while (!future.isDone()){
            //执行一些其他任务
        }
        try {
            Integer bytesRead = future.get(); //获取结果
            System.out.println("Bytes read: "+bytesRead);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
