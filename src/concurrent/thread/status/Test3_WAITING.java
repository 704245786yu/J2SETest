package concurrent.thread.status;

/**验证Object.wait()触发的线程WAITING状态*/
public class Test3_WAITING {
    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.start();
        Thread.sleep(100);
        PrintThreadState.print("main() ", thread1); //WAITING
    }
}

class Thread1 extends Thread{
    public static final Object obj = new Object();

    @Override
    public void run() {
        try {
            synchronized (obj){
                obj.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}