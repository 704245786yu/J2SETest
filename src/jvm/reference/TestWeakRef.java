package jvm.reference;

import java.lang.ref.WeakReference;

/**弱引用示例*/
public class TestWeakRef {
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
		WeakReference<User> userWeakRef = new WeakReference<>(u);
		u = null;
		System.out.println(userWeakRef.get());
		System.gc();
		//不管当前内存空间足够与否，都会回收它的内存
		System.out.println("After GC:");
		System.out.println(userWeakRef.get());
	}
}
