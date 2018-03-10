package DesignPattern.Observer.demo3Observable;

import java.util.Observable;
import java.util.Observer;

/**通过实现Java的Observer，实现“观察者”
 * */
public class ObserverImpl1 implements Observer {

	/**@param arg Observable传递过来的参数
	 * */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("ObserverImpl1收到数据："+arg);
	}

}
