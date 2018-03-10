package jvm;

import java.util.ArrayList;
import java.util.List;

public class TestHeapOOM {
	
	/**测试OutOfMemoryError: Java heap space
	 * VM Args: -Xms10M -Xmx10M -XX:+HeapDumpOnOutOfMemoryError
	 * -Xms：堆的最小值
	 * -Xmx：堆的最大值
	 * -XX:+HeapDumpOnOutOfMemoryError：让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析。
	 * */
	public static void main(String[] args){
		List<OOM> list = new ArrayList<OOM>();
		while(true){
			list.add(new OOM());
		}
	}
}

class OOM{
	
}