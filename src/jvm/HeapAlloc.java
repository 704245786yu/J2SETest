package jvm;

/**最大堆、初始堆（最小堆）、系统可用内存，这三者的含义和彼此之间的关系*/
public class HeapAlloc {

	public static void main(String[] args) {
		printMem();
		
		byte[] b = new byte[1*1024*1024];
		System.out.println("分配了1M空间给数组");
		
		printMem();
		
		b = new byte[4*1024*1024];
		System.out.println("分配了4M空间给数组");
		
		printMem();
	}
	
	/**打印当前堆内存情况*/
	public static void printMem(){
		Runtime runtime = Runtime.getRuntime();
		System.out.println("max mem="+runtime.maxMemory()+" bytes");//最大可用内存
		System.out.println("free mem="+runtime.freeMemory()+" bytes");//当前空闲内存（该值等于，当前总内存减去当前已使用的空间）
		System.out.println("total mem="+runtime.totalMemory()+" bytes");//当前总内存（该值总是在-Xms和-Xmx之间）
	}
}
