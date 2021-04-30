package DesignPattern.Observer.demo3Observable;

import java.util.Observable;

/**通过继承Java的Observable，实现“被观察者”。
 * */
public class WeatherData extends Observable {

	public void setMeasurements(float temperature){
		System.out.println("WeatherData：接收到新数据了，开始通知所有观察者");
		//Observable的changed变量可用于控制是否确实要执行通知操作。
		super.setChanged();
		super.notifyObservers(temperature); //每次通知完后，都会清除changed状态
	}
}
