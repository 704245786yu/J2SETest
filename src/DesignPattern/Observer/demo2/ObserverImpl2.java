package DesignPattern.Observer.demo2;

/**观察者的实现类
 * */
public class ObserverImpl2 implements Observer{

	@Override
	public void update(float temp, float humidity) {
		System.out.println("ObserverImpl2: "+temp+" "+humidity);
	}

}
