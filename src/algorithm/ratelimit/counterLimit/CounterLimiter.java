package algorithm.ratelimit.counterLimit;

import java.util.concurrent.atomic.AtomicInteger;

/**固定窗口计数器算法*/
public class CounterLimiter {
    private int windowSize; //窗口大小，毫秒为单位
    private int limit;  //窗口内限流大小
    private AtomicInteger count;    //当前窗口的计数器

    public CounterLimiter(int windowSize, int limit){
        this.windowSize = windowSize;
        this.limit = limit;
        count = new AtomicInteger(0);

        //开启一个线程，达到窗口结束时清空count
        new Thread(()->{
            while(true){
                count.set(0);
                try {
                    Thread.sleep(windowSize);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean tryAcquire(){
        int newCount = count.addAndGet(1);
        if(newCount > limit){
            return false;
        }else{
            return true;
        }
    }

    //测试
    public static void main(String[] args) {
        //每秒20个请求
        CounterLimiter counterLimiter = new CounterLimiter(1000, 20);
        int count = 0;

        //模拟50次请求，看多少能通过
        for (int i=0; i<50; i++){
            if(counterLimiter.tryAcquire()){
                count++;
            }
        }
        System.out.println("第一次发50个请求，通过："+count+"，限流："+(50-count));
    }
}
