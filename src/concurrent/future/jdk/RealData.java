package concurrent.future.jdk;

import java.util.concurrent.Callable;

/**Callable接口只有一个call()方法，用于构造并返回实际数据。该接口是Future框架和应用程序间的重要接口。
 * 通常我们会使用Callable实例作为参数来构造一个FutureTask实例，并将FutureTask提交给线程池。
 * */
public class RealData implements Callable<String> {

	private String param;
	
	public RealData(String param) {
		this.param = param;
	}

	@Override
	public String call() throws Exception {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<10; i++){
			sb.append(param);
			try {
				Thread.sleep(100);	//使用sleep()模拟RealData的缓慢构造过程
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
