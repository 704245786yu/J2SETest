package concurrent.thread.status;

public class PrintThreadState {
    private static String str = "%s，id：%s，状态：%s";

    public static void print(String prefix){
        Thread thread = Thread.currentThread();
        System.out.println(prefix + String.format(str, thread.getName(), thread.getId(), thread.getState()));
    }

    public static void print(String prefix, Thread thread){
        System.out.println(prefix + String.format(str, thread.getName(), thread.getId(), thread.getState()));
    }
}
