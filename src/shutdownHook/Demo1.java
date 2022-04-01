package shutdownHook;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**优雅停服*/
public class Demo1 {
    /**监控服务*/
    private ScheduledThreadPoolExecutor monitorService;

    public Demo1(){
        monitorService = new ScheduledThreadPoolExecutor(1);
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.start();

        //添加钩子，实现优雅停服
        final Demo1 demo1Ref = demo1;
        Runtime.getRuntime().addShutdownHook(new Thread("shutdown-hook"){
            @Override
            public void run(){
                System.out.println("接收到退出信号，开始释放资源，进行优雅停服");
                demo1Ref.stop();
            }
        });
        System.out.println("服务启动完成");
    }

    /**启动监控服务，监控内存信息*/
    public void start(){
        System.out.println(String.format("启动监控服务, threadId:%d, threadName:%s", Thread.currentThread().getId(), Thread.currentThread().getName()));
        monitorService.scheduleWithFixedDelay(()->{
            System.out.println(String.format("Max mem: %dm, allocated mem: %dm, remain mem: %dm, free mem: %dm",
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024));
        }, 2, 2, TimeUnit.SECONDS);
    }

    /**该方法用于在退出程序之前释放资源。主要用于关闭线程池。*/
    public void stop(){
        System.out.println(String.format("开始关闭线程池, threadId:%d, threadName:%s", Thread.currentThread().getId(), Thread.currentThread().getName()));
        monitorService.shutdown();
        try {
            monitorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for monitor service to stop");
            e.printStackTrace();
        }
        if(!monitorService.isTerminated()){
            monitorService.shutdownNow();
            try {
                while(!monitorService.isTerminated()){
                    monitorService.awaitTermination(10, TimeUnit.SECONDS);
                }
            } catch (InterruptedException e) {
                System.err.println("Interrupted while waiting for monitor service to stop");
                e.printStackTrace();
            }
        }
        System.out.println(String.format("线程池关闭完成, threadId:%d, threadName:%s", Thread.currentThread().getId(), Thread.currentThread().getName()));
    }

}
