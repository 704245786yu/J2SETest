package concurrent.future.simulationFuture;

/**Client主要实现了获取FutureData，并开启构造RealData的线程。在接收到请求后（被调用了request()方法）,很快的返回FutureData。
 * 它不会等待数据真的构造完毕再返回，而是立即返回FutureData，即使这个时候FutureData内并没有真实数据。
 * */
public class Client {

	public Data request(final String queryStr){
		final FutureData future = new FutureData();
		new Thread(){
			//RealData的构建很慢，所以在单独的线程中进行
			@Override
			public void run() {
				RealData realData = new RealData(queryStr);
				future.setRealData(realData);
			}
		}.start();
		return future;	//FutureData会被立即返回
	}
}
