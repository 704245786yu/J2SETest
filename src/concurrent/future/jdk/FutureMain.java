package concurrent.future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**JDK的Future模式示例
 * java.util.concurrent.Future 用于获取真实的数据。
 * java.util.concurrent.RunnableFuture#run()用于构造真实的数据。
 * FutureTask类实现了RunnableFuture接口。
 * https://www.jianshu.com/p/a1917e0ce58f
 * */
public class FutureMain {

	public static void main(String[] args) {
		FutureTask<String> futureTask = new FutureTask<>(new RealData("a"));
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//执行FutureTask，相当于simulationFuture包的例子中client.request("a")方法。
		//在这里开启线程进行RealData的call()调用
		executor.submit(futureTask);
		
		try {
			//使用sleep()模拟对其他业务逻辑的处理，与此同时RealData被创建，从而充分利用了等待时间
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        System.out.println("请求完毕");
		//取得call()方法的返回值。这里的调用相当于simulationFuture包的例子中data.getResult()。
		//如果此时call()方法没有执行完成，则依然会等待
		try {
			System.out.println("数据："+futureTask.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

        executor.shutdown();
	}
}
