package jvm.generation;

import java.util.HashMap;
import java.util.Map;

/**演示大对象进入老年代。如果对象体积太大，新生代无论eden区或者survivor区无法容纳这个对象，因此会被直接晋升到老年代。
 * -Xmx32M -Xms32M -XX:+PrintGCDetails -XX:+UseSerialGC -XX:PretenureSizeThreshold=1000 -XX:-UseTLAB
 * -XX:PretenureSizeThreshold：设置对象直接晋升到老年代的阈值，单位字节。该参数只对串行回收器和ParNew有效，对ParallelGC无效。默认0，也就是不指定最大的晋升大小，由运行情况决定。
 * -XX:-UseTLAB：禁用TLAB。
 * */
public class PretenureSizeThreshold {
	public static final int _1K = 1024;
	public static void main(String[] args) {
		Map<Integer,byte[]> map = new HashMap<>();
		//申请5M的空间
		for(int i=0; i<5*_1K; i++){
			byte[] b = new byte[_1K];
			map.put(i, b);
		}
	}
}
