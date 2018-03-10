package DesignPattern.Observer.demo2;

import org.junit.Test;

public class Test1 {

	/**与demo1中的观察者模式的实现方式稍有不同，
	 * Observer实现类中，不再保存Observer接口对象的引用，也不再主动注册了。注册由Subject对象自己完成。
	 * */
	@Test
	public void test1(){
		WeatherData weatherData = new WeatherData();
		ObserverImpl1 o1 = new ObserverImpl1();
		ObserverImpl2 o2 = new ObserverImpl2();
		
		weatherData.registerObserver(o1);
		weatherData.registerObserver(o2);
		/*打印的内容如下
		 * ObserverImpl1: 1.0 2.0
		 * ObserverImpl2: 1.0 2.0
		 * 注意：打印的顺序为注册的顺序。
		 * */
		weatherData.setMeasurements(1.0f, 2.0f);
	}
}
