package concurrent.threadPool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**演示获取一个数组里的最大值
 * ForkJoin是JDK1.7加入的新的线程池实现，它体现的是一种分治思想，适用于能够进行任务拆分的CPU密集型运算。
 * 所谓任务拆分，是将一个大任务拆分为算法上相同的小任务，直至不能拆分可以直接求解。默认会创建与CPU核心数大小相同的线程池。
 * */
public class ForJoinTest {
    /**RecursiveTask用于获取任务的返回值。
     * RecursiveAction不关心返回值，仅执行任务。
     */
    private static class MyTask extends RecursiveTask<Integer>{
        int[] ary;
        public MyTask(int[] ary){
            this.ary = ary;
        }

        @Override
        protected Integer compute() {
            if(ary.length <= 2){    //做实际的业务操作
                if(ary.length == 1) return ary[0];
                else return ary[0]>ary[1] ? ary[0] : ary[1];
            }else{
                int mid = ary.length / 2;
                MyTask leftTask = new MyTask(Arrays.copyOf(ary, mid));
                MyTask rightTask = new MyTask(Arrays.copyOfRange(ary, mid, ary.length));
//                invokeAll(leftTask, rightTask); //新分解出来的任务交给框架去执行
                leftTask.fork();    //或者使用此方式让线程执行此任务
                rightTask.fork();
                //通过join()获取任务执行后的值
                return leftTask.join() > rightTask.join() ? leftTask.join() : rightTask.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] ary = {1,23,46,7,89,43,67};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyTask myTask = new MyTask(ary);
        forkJoinPool.invoke(myTask);
        System.out.println(myTask.join());
    }
}
