package jvm.generation;

import java.util.HashMap;
import java.util.Map;

/**-Xmx1024M -Xms1024M -XX:+PrintGCDetails -XX:MaxTenuringThreshold=15 -XX:+PrintHeapAtGC
 * -XX:MaxTenuringThreshold：控制新生代对象的最大晋升年龄，对象的年龄是由对象经历过的GC次数决定的，GC会在这个数值内将对象晋升到老年代。
 * */
public class TestMaxTenuringThreshold {
	public static final int _1M = 1024*1024;
	public static final int _1K = 1024;
	public static void main(String[] args) {
		Map<Integer,byte[]> map = new HashMap<>();
		//申请5M的空间
		for(int i=0; i<5*_1K; i++){
			byte[] b = new byte[_1K];
			map.put(i, b);
		}
		
		//不断的往新生代分配内存，以触发新生代GC
		for(int i=0; i<17*270; i++){	//17是用来保证超过对象的年龄15。
			byte[] g = new byte[_1M];
		}
	}
}
