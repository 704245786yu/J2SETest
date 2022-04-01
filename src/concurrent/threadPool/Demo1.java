package concurrent.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiyu
 * @Date 2020-10-29
 */
public class Demo1 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 100, 0, TimeUnit.MICROSECONDS,
            new LinkedBlockingQueue<>(1024), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        Runnable runnable = ()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        int count = 1125;
        for (int i=0; i<count; i++){
            threadPoolExecutor.execute(runnable);
        }

        while(true){
            System.out.println();
            System.out.println("当前排队任务数："+threadPoolExecutor.getQueue().size());
            System.out.println("当前活动任务数："+threadPoolExecutor.getActiveCount());
            System.out.println("执行完成任务数："+threadPoolExecutor.getCompletedTaskCount());
            System.out.println("总任务数："+threadPoolExecutor.getTaskCount());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
