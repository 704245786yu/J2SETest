package jvm.generation;

/**-Xmx64M -Xms64M -XX:+PrintGCDetails
Heap
 PSYoungGen      total 18944K, used 1967K [0x00000000feb00000, 0x0000000100000000, 0x0000000100000000)
  eden space 16384K, 12% used [0x00000000feb00000,0x00000000fecebd58,0x00000000ffb00000)
  from space 2560K, 0% used [0x00000000ffd80000,0x00000000ffd80000,0x0000000100000000)
  to   space 2560K, 0% used [0x00000000ffb00000,0x00000000ffb00000,0x00000000ffd80000)
 ParOldGen       total 44032K, used 0K [0x00000000fc000000, 0x00000000feb00000, 0x00000000feb00000)
  object space 44032K, 0% used [0x00000000fc000000,0x00000000fc000000,0x00000000feb00000)
 Metaspace       used 2576K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
 * */
public class TestAllocEden {

	public static final int _1k = 1024;
	public static void main(String[] args) {
		//根据打印可知，整个运行过程中没有GC发生，eden区占据了16384*12%=1966.08，约为2M，分配的5K数据都在eden中。
		//from、to和老年代均未使用。
		for(int i=0; i<5;i++){
			byte[] b = new byte[_1k];
		}
	}
}
