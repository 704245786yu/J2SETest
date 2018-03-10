package DesignPattern.Observer.demo2;

import java.util.ArrayList;

public class WeatherData implements Subject {

	//用于保存所有注册的观察者。用ArrayList可以保证通知观察者的顺序，但若不在registerObserver()加以控制，会产生同一对象多次注册的问题。若不在意顺序可使用Set。
	private ArrayList<Observer> observers = new ArrayList<>();;
	
	private float temperature;
	private float humidity;
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/**之所以需要这样的一个通知方法，是为了Subject接口实现类里的其他方法方便的通知观察者。*/
	@Override
	public void nofifyObservers() {
		for(int i=0; i<observers.size(); i++){
			observers.get(i).update(temperature, humidity);//通过调用Observer接口的update()实现通知。
		}
	}

	/**当观测值发生变化时通知观察者*/
	public void setMeasurements(float temperature, float humidity){
		this.temperature = temperature;
		this.humidity = humidity;
		nofifyObservers();
	}
}
