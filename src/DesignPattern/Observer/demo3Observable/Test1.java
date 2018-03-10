package DesignPattern.Observer.demo3Observable;

import org.junit.Test;

public class Test1 {

	/**使用Java.util里的Observable和Observer来简化实现观察者模式
	 * */
	@Test
	public void test1(){
		ObserverImpl1 o1 = new ObserverImpl1();
		WeatherData weatherData = new WeatherData();
		weatherData.addObserver(o1);
		weatherData.setMeasurements(0.2f);
	}
}
