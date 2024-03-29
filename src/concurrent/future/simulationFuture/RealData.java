package concurrent.future.simulationFuture;

/**RealData是用户要获取的真实数据*/
public class RealData implements Data {

	protected final String result;
	
	public RealData(String param) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<10; i++){
			sb.append(param);
			try {
				Thread.sleep(100);	//使用sleep()模拟RealData的缓慢构造过程
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		result = sb.toString();
	}

	@Override
	public String getResult() {
		return result;
	}

}
