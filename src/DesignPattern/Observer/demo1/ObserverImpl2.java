package DesignPattern.Observer.demo1;

/**观察者的实现类
 * */
public class ObserverImpl2 implements Observer{

	private Subject weatherData;
	
	public ObserverImpl2(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void update(float temp, float humidity) {
		System.out.println("ObserverImpl2: "+temp+" "+humidity);
	}

}
