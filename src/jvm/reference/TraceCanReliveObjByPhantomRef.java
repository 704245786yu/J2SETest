package jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**使用虚引用跟踪一个可复活对象的回收
 * 虚引用必须和引用队列一起使用，它的作用在于跟踪垃圾回收过程。
 * */
public class TraceCanReliveObjByPhantomRef {

	public static TraceCanReliveObjByPhantomRef canReliveObj;
	
	static ReferenceQueue<TraceCanReliveObjByPhantomRef> phantomQueue = null;
	
	/**跟踪引用队列，打印对象的回收情况*/
	public static class CheckRefQueueThread extends Thread{
		@Override
		public void run(){
			while(true){
				if(phantomQueue != null){
					PhantomReference<TraceCanReliveObjByPhantomRef> obj = null;
					try {
						obj = (PhantomReference)phantomQueue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(obj != null)
						System.out.println("TraceCanReliveObjByPhantomRef is delete by GC");
				}
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize() called");
		canReliveObj = this;
	}

	@Override
	public String toString() {
		return "I am TraceCanReliveObjByPhantomRef";
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new CheckRefQueueThread();
		t.setDaemon(true);
		t.start();
		
		canReliveObj = new TraceCanReliveObjByPhantomRef();
		phantomQueue = new ReferenceQueue<TraceCanReliveObjByPhantomRef>();
		PhantomReference<TraceCanReliveObjByPhantomRef> phantomRef = new PhantomReference<>(canReliveObj, phantomQueue);
		System.out.println("phantomRef.get()："+phantomRef.get());//虚引用的get方法始终返回null 
		
		canReliveObj = null;
		System.gc();
		Thread.sleep(500);//为了保证finalize()被调用
		if(canReliveObj==null)
			System.out.println("canReliveObj is null");
		else
			System.out.println("canReliveObj is alive");
		
		System.out.println("第2次 GC");
		canReliveObj = null;
		System.gc();
		Thread.sleep(500);//为了保证finalize()被调用
		if(canReliveObj==null)
			System.out.println("canReliveObj is null");
		else
			System.out.println("canReliveObj is alive");
	}
}
