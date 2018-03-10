package jvm;

import java.util.HashMap;

/**演示Stop-The-World，垃圾回收时导致的所有其他线程停顿
 * -Xmx1g -Xms1g -Xmn512k（新生代）-XX:+UseSerialGC -Xloggc:gc.long -XX:+PrintGCDetails
 * */
public class TestStopWorld {

	public static class MyThread extends Thread{
		HashMap map = new HashMap();
		@Override
		public void run() {
			try{
				while(true){
					//当内存消耗大于900M时，清空内存，防止内存溢出
					if(map.size()*512/1024/1024>=900){
						map.clear();
						System.out.println("clean map");
					}
					byte[] b1;
					for(int i=0; i<100; i++){
						b1 = new byte[512];
						map.put(System.nanoTime(), b1);
					}
					Thread.sleep(1);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	/**该线程每0.1秒打印一次，用于检测GC回收导致的线程停顿*/
	public static class PrintThread extends Thread{
		public static final long startTime = System.currentTimeMillis();
		@Override
		public void run() {
			try{
				while(true){
					long t = System.currentTimeMillis()-startTime;
					System.out.println(t/1000+"."+t%1000);
					Thread.sleep(100);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyThread t = new MyThread();
		PrintThread p = new PrintThread();
		t.start();
		p.start();
	}
}
