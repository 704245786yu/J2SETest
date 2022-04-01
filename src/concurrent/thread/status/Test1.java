package concurrent.thread.status;

/**演示线程的 NEW、RUNNABLE、TIMED_WAITING、TERMINATED 状态。
 * 这里的 TIMED_WAITING 由 sleep() 触发。
 * */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        PrintThreadState.print("执行 main() 1，", myThread);   //Thread-0 NEW
        myThread.start();
        Thread.sleep(1000);
        PrintThreadState.print("执行 main() 2，", myThread);   //Thread-0 TIMED_WAITING
        Thread.sleep(2100); //等待MyThread执行完毕
        PrintThreadState.print("执行 main() 3，", myThread);   //Thread-0 TERMINATED
    }
}

class MyThread extends Thread{
    public MyThread(){
        PrintThreadState.print("执行 MyThread()，");   //main RUNNABLE
    }

    @Override
    public void run(){
        PrintThreadState.print("执行 run()，");    //Thread-0 RUNNABLE
        System.out.println("Mythread begin sleep");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Mythread end sleep");
    }
}