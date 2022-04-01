package aio.asynFileChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**使用异步IO API读取文件的其中一种形式是Callback模式。
 * 摘自《Java程序员修炼之道》
 * @author zhiyu
 * @Date 2019-11-14
 */
public class CallbackMode {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("e:/hello.groovy");
        //FutureMode.java已有说明
        AsynchronousFileChannel asynFileChannel = AsynchronousFileChannel.open(path);

        ByteBuffer byteBuffer = ByteBuffer.allocate(100_000);
        //从通道中读取数据
        asynFileChannel.read(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            //读取完成时的回调方法
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("Bytes read: "+ result);
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //读取失败时的回调方法
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }
}
