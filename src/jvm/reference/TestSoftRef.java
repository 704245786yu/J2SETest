package jvm.reference;

import java.lang.ref.SoftReference;

/**演示软引用
 * 一个对象只持有软引用，那么当堆空间不足时，就会被回收
 * -Xmx10m
 * */
public class TestSoftRef {

	public static class User{
		public int id;
		public String name;
		
		public User(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "User：id=" + id + ", name=" + name;
		}
	}
	
	public static void main(String[] args) {
		User u = new User(1,"aaa");
		SoftReference<User> userSoftRef = new SoftReference<>(u);
		u = null;
		System.out.println(userSoftRef.get());
		System.gc();
		System.out.println("After GC:");
		System.out.println(userSoftRef.get());
		
		byte[] b = new byte[1024*950*7];	//分配一块较大的内存(约为7M，该数值是根据本机系统及JDK环境调试过的，换了执行环境不代表执行效果一样)，让系统认为内存资源紧张
		System.gc();//这行是多余的，因为在分配大数据时，系统会自动进行GC，即资源不够用时会先GC，然后再分配资源。
		System.out.println(userSoftRef.get());	//由于内存不够用，GC时会删除软引用，所以打印null。
	}
}
