package DesignPattern.Observer.demo2;

/**观察者*/
public interface Observer {

	/**Subject会通过调用此方法来将改变的状态值传递给观察者
	 * @param temp 温度
	 * @param humidity 湿度
	 * */
	public void update(float temp, float humidity);
}
