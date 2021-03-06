package DesignPattern.Observer.demo1;

/**观察者的实现类，由于采用实现接口的方式，因此还可以实现其他你想实现的接口。
 * */
public class ObserverImpl1 implements Observer{

	private Subject weatherData;	//保存Subject的引用，是为了以后方便取消注册。
	
	public ObserverImpl1(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);	//向weatherData注册本对象
	}

	@Override
	public void update(float temp, float humidity) {
		//接收到主题发送来的通知信息后，可以做任何事情
		System.out.println("ObserverImpl1: "+temp+" "+humidity);
	}

}
