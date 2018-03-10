package jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**软引用队列
 * -Xmx10m -XX:+PrintGC
 * */
public class TestSoftRefQueue {

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
	
	/**扩展软引用的目的是记录User.uid，后续在引用队列中，就可以通过这个uid字段知道哪个User实例被回收了*/
	public static class UserSoftReference extends SoftReference<User>{
		int uId;
		public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
			super(referent, q);
			uId = referent.id;
		}
	}
	
	//引用队列
	static ReferenceQueue<User> softQueue = null;
	
	/**跟踪引用队列，打印对象的回收情况*/
	public static class CheckRefQueueThread extends Thread{
		@Override
		public void run(){
			while(true){
				if(softQueue != null){
					UserSoftReference obj = null;
					try {
						obj = (UserSoftReference)softQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(obj != null)
						System.out.println("user id "+obj.uId+" is delete");
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new CheckRefQueueThread();
		t.setDaemon(true);
		t.start();
		User u = new User(1,"aaa");
		softQueue = new ReferenceQueue<User>();
		/*创建软引用时，指定了一个软引用队列，当给定的对象实例被回收时，就会被加入这个引用队列，
		 * 通过访问该队列可以跟踪对象的回收情况。
		 */
		UserSoftReference userSoftRef = new UserSoftReference(u, softQueue);
		u = null;
		System.out.println(userSoftRef.get());
		System.gc();
		System.out.println("After GC:");
		System.out.println(userSoftRef.get());//内存足够，不会被回收
		
		System.out.println("try to create byte array and GC");
		byte[] b = new byte[1024*950*7];
		System.gc();
		System.out.println(userSoftRef.get());
		
		Thread.sleep(1000);
	}
}
