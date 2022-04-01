package concurrent.JMM;

/**
 * @author zhiyu
 * @Date 2020-10-24
 */
public class Demo1 {
    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("waiting data...");
            while(!initFlag){}
            System.out.println("success");
        }).start();

        Thread.sleep(1000);

        new Thread(()-> prepareData()).start();
    }

    public static void prepareData(){
        System.out.println("prepare data...");
        initFlag = true;
        System.out.println("prepare data end.");
    }
}
