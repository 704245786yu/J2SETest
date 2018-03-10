package jvm.gc;

/**代码演示了Java垃圾回收器采用的是可达性分析算法，而不是引用计数算法来管理内存。
 * a和b被相互引用，但却没有别的引用，依然会被回收*/
public class NonReferenceCountingGC {

	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	//这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
	private byte[] bigSize = new byte[2 * _1MB];
	
	public static void main(String[] args) {
		NonReferenceCountingGC a = new NonReferenceCountingGC();
		NonReferenceCountingGC b = new NonReferenceCountingGC();
		a.instance = b;
		b.instance = a;
		
		a=null;
		b=null;
		
		//假设在这行发生GC，a和b是否能被回收？
		System.gc();
	}
}
