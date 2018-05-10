package concurrent.future.jdk;

import java.util.concurrent.Callable;

/**Callable接口只有一个call()方法，用于构造实际的数据。*/
public class RealData implements Callable<String> {

	private String param;
	
	public RealData(String param) {
		this.param = param;
	}

	@Override
	public String call() throws Exception {
		//使用sleep()模拟RealData的缓慢构造过程
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<10; i++){
			sb.append(param);
			try {
				Thread.sleep(100);	//使用sleep()模拟一个很慢的操作过程
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
