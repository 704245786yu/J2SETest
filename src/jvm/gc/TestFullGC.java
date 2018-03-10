package jvm.gc;

/**进行了一次简单的Full GC
 * */
public class TestFullGC {

	/**-XX:+PrintGCDetails -XX:+UseSerialGC
	 * 使用以上参数GC时打印的内容：
		[Full GC (System.gc()) [Tenured: 0K->526K(83328K), 0.0078052 secs] 2662K->526K(120768K), [Metaspace: 2570K->2570K(1056768K)], 0.0081072 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
		Heap
		 def new generation   total 37568K, used 334K [0x0000000086600000, 0x0000000088ec0000, 0x00000000aeea0000)
		  eden space 33408K,   1% used [0x0000000086600000, 0x00000000866539c8, 0x00000000886a0000)
		  from space 4160K,   0% used [0x00000000886a0000, 0x00000000886a0000, 0x0000000088ab0000)
		  to   space 4160K,   0% used [0x0000000088ab0000, 0x0000000088ab0000, 0x0000000088ec0000)
		 tenured generation   total 83328K, used 526K [0x00000000aeea0000, 0x00000000b4000000, 0x0000000100000000)
		   the space 83328K,   0% used [0x00000000aeea0000, 0x00000000aef23840, 0x00000000aef23a00, 0x00000000b4000000)
		 Metaspace       used 2576K, capacity 4486K, committed 4864K, reserved 1056768K
		  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K

	 * -XX:+PrintGCDetails -XX:+UseParallelOldGC
	 * 使用以上参数GC时打印的内容：
		[GC (System.gc()) [PSYoungGen: 2498K->632K(36352K)] 2498K->640K(119808K), 0.0034046 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
		[Full GC (System.gc()) [PSYoungGen: 632K->0K(36352K)] [ParOldGen: 8K->526K(83456K)] 640K->526K(119808K), [Metaspace: 2570K->2570K(1056768K)], 0.0292257 secs] [Times: user=0.02 sys=0.00, real=0.03 secs] 
		Heap
			PSYoungGen      total 36352K, used 312K [0x00000000d7780000, 0x00000000da000000, 0x0000000100000000)
				eden space 31232K, 1% used [0x00000000d7780000,0x00000000d77ce2b8,0x00000000d9600000)
				from space 5120K, 0% used [0x00000000d9600000,0x00000000d9600000,0x00000000d9b00000)
				to   space 5120K, 0% used [0x00000000d9b00000,0x00000000d9b00000,0x00000000da000000)
			ParOldGen       total 83456K, used 526K [0x0000000086600000, 0x000000008b780000, 0x00000000d7780000)
				object space 83456K, 0% used [0x0000000086600000,0x0000000086683840,0x000000008b780000)
			Metaspace       used 2576K, capacity 4486K, committed 4864K, reserved 1056768K
				class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
		
		根据打印可知，对于并行回收器的Full GC（使用UseParallelOldGC或UseParallelGC），在每一次Full GC前都会伴随一次新生代GC。而串行回收器不会。
	 * */
	public static void main(String[] args) {
		System.gc();
	}
}
