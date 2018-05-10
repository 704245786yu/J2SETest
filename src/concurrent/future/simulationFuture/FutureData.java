package concurrent.future.simulationFuture;

/**FutureData是Future模式的关键。它实际上是真实数据RealData的代理，封装了获取RealData的等待过程。
 * 因此，它是可以很快构造并获取的。*/
public class FutureData implements Data {

	protected RealData realData = null; //FutureData是RealData的包装
	protected boolean isReady = false;	//用于表示真实数据RealData是否已经准备好
	
	public synchronized void setRealData(RealData realData){
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		this.notifyAll();	//【关键操作！】,设置完后要通知等待真实数据的线程。RealData已被注入，通知getResult()。
	}
	
	/**该方法会等待RealData即真实数据构造完成
	 * */
	@Override
	public String getResult() {
		while(!isReady){
			try {
				wait();	//一直等待，直到RealData被注入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.result;
	}

}
