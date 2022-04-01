package concurrent.future.simulationFuture;

/**FutureData是Future模式的关键。它是真实数据RealData的代理，封装了获取RealData的等待过程。
 * 因此，它是可以很快构造并获取的。*/
public class FutureData implements Data {

	protected RealData realData = null; //FutureData包装了RealData
	protected boolean isReady = false;	//标识RealData是否已经准备好
	
	public synchronized void setRealData(RealData realData){
	    //防止重复设置
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		notifyAll();	//【关键操作！】,set完后要通知等待RealData的线程，即通知getResult()。
	}
	
	/**该方法会等待RealData构造完成*/
	@Override
	public synchronized String getResult() {
		while(!isReady){    //此处为何要用while？怀疑是防止线程并非被正常唤醒，而是因为发生了其他中断异常，导致唤醒，所以醒了后要再判断一次数据是否设置成功。
			try {
				wait();	//一直等待，直到RealData被注入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.result;
	}

}
