package concurrent.thread.status;

import java.util.concurrent.locks.ReentrantLock;

/**验证线程的 BLOCKED、WAITING 状态。
 * synchronized 会导致 BLOCKED，Lock#lock()会导致WAITING。*/
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始测试synchronized");
        SynRun synRun = new SynRun();
        Thread a = new Thread(synRun, "a");
        a.start();
        Thread b = new Thread(synRun, "b");
        b.start();
        PrintThreadState.print("main()1 ", a);    //a RUNNABLE / TIMED_WAITING
        Thread.sleep(1000);
        PrintThreadState.print("main()2 ", a);    //a TIMED_WAITING
        PrintThreadState.print("main()3 ", b);    //b BLOCKED

        Thread.sleep(3100);
        System.out.println("\n开始测试Lock");

        LockRun lockRun = new LockRun();
        a = new Thread(lockRun, "a1");
        a.start();
        b = new Thread(lockRun, "b1");
        b.start();
        PrintThreadState.print("main()4 ", a);    //a RUNNABLE
        Thread.sleep(1000);
        PrintThreadState.print("main()5 ", a);    //a TIMED_WAITING
        PrintThreadState.print("main()6 ", b);    //b WAITING
    }
}

class SynRun implements Runnable{
    @Override
    public void run() {
        Service1.method1();
    }
}

class LockRun implements Runnable{
    @Override
    public void run() {
        Service2.method1();
    }
}

class Service1{
    synchronized static public void method1() {
        System.out.println(Thread.currentThread().getName() + " run method1");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Service2{
    static ReentrantLock reentrantLock = new ReentrantLock();
    static public void method1() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + " run method1");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}