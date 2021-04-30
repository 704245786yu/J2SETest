package DesignPattern.Singleton;

/**DCL（Double-checked Locking 双锁检测）单例模式
 * Double-checked：为创建单例，而需要检查两次实例对象是否为空。
 * */
public class DCL {

	private volatile static DCL instance;//注意：这里必须使用volatile修饰，否则在多线程情况下会发生对象半初始化问题
	
	public static DCL getInstance(){
		if(instance == null){
			synchronized(DCL.class){
				if(instance == null)
					instance = new DCL();
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		DCL.getInstance();
	}
}
