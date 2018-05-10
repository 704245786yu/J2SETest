package concurrent.future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**测试使用JDK的Future模式实现*/
public class FutureMain {

	public static void main(String[] args) {
		FutureTask<String> future = new FutureTask<String>(new RealData("a"));
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//执行FutureTask，相当于simulationFuture包的例子中client.request("a")方法。
		//在这里开启线程进行RealData的call()调用
		executor.submit(future);
		
		System.out.println("请求完毕");
		try {
			//使用sleep()模拟对其他业务逻辑的处理，在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//取得call()方法的返回值。这里的调用相当于simulationFuture包的例子中data.getResult()。
		//如果此时call()方法没有执行完成，则依然会等待
		try {
			System.out.println("数据："+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
