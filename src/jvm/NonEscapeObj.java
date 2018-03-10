package jvm;

/**演示非逃逸对象在栈上分配，如果堆空间小于Xmx设置的值，就必然会发生GC。
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
 * -server：在Server模式下执行程序，因为在该模式下，才可以启动逃逸分析
 * -XX:+DoEscapeAnalysis 启用逃逸分析，默认是开启的。
 * -XX:+PrintGC：如果发生GC，将会打印GC日志
 * -XX:-UseTLAB：关闭TLAB
 * -XX:+EliminateAllocations：开启了标量替换（默认打开），允许将对象打散分配在栈上，比如对象拥有id和name两个字段，
 * 			那么这两个字段将会被视为两个独立的局部变量进行分配。
 * */
public class NonEscapeObj {

	public static class User{
		public int id = 0;
		public String name = "";
	}
	
	public static void alloc(){
		User u = new User();
		u.id = 5;
		u.name = "geym";
	}
	
	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		//由于User需要占据约16字节的空间，因此累计分配16*100_000_000字节的内存，大约占用1600M，
		//堆空间小于这个值，就必然会发生GC。
		for(int i=0; i<100_000_000; i++){
			alloc();
		}
		long b = System.currentTimeMillis();
		System.out.println(b-a);
	}
}
