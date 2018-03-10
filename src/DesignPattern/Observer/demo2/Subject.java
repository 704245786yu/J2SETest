package DesignPattern.Observer.demo2;

/**主题*/
public interface Subject {

	/**注册观察者*/
	public void registerObserver(Observer o);
	
	/**移除观察者*/
	public void removeObserver(Observer o);
	
	/**当有事件时，这个方法会被调用，以通知所有的观察者。具体来讲，因为观察者都约定实现了update()方法，所以通过调用update()通知观察者。*/
	public void nofifyObservers();
}
