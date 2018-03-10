package DesignPattern.Observer.demo1;

import org.junit.Test;

public class Test1 {

	/**最简单的观察者模式示例。
	 * 该示例演示了最简单的自己实现的观察者模式。仅通过两个主要的接口Subject和Observer即可实现。
	 * */
	@Test
	public void test1(){
		WeatherData weatherData = new WeatherData();
		ObserverImpl1 o1 = new ObserverImpl1(weatherData);
		ObserverImpl2 o2 = new ObserverImpl2(weatherData);
		
		/*打印的内容如下
		 * ObserverImpl1: 1.0 2.0
		 * ObserverImpl2: 1.0 2.0
		 * 注意：打印的顺序为注册的顺序。
		 * */
		weatherData.setMeasurements(1.0f, 2.0f);
	}
}
