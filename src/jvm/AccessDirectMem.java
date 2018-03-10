package jvm;

import java.nio.ByteBuffer;

/**演示对直接内存和堆内存的分配速度、读写速度
 * 根据结果表明：内存分配上，堆内存快于直接内存；访问（读写）上，直接内存快于堆内存。
 * 所以，直接内存适合申请次数少、访问频繁的场合；内存空间需要频繁申请的不适合使用直接内存。
 * */
public class AccessDirectMem {

	public static void main(String[] args) {
		AccessDirectMem adm = new AccessDirectMem();
		adm.accessMemTime();
//		adm.allocMemTime();
	}

	/**比较堆内存与直接内存的访问时间*/
	public void accessMemTime(){
		heapAccess();
		directAccess();
		
		heapAccess();
		directAccess();
	}
	
	/**比较堆内存与直接内存的分配时间*/
	public void allocMemTime(){
		heapAllocate();
		directAllocate();
		
		heapAllocate();
		directAllocate();
	}
	
	/**直接内存访问耗时*/
	public void directAccess(){
		ByteBuffer b = ByteBuffer.allocateDirect(500);//直接内存的分配时间要大于堆内存的分配时间
		long start = System.currentTimeMillis();
		for(int i=0; i<100000; i++){
			for(int j=0; j<99; j++)
				b.putInt(j);
			b.flip();
			for(int j=0; j<99; j++)
				b.getInt();
			b.clear();
		}
		long end = System.currentTimeMillis();
		System.out.println("direct mem write:"+(end-start));
	}
	
	/**堆内存访问耗时*/
	public void heapAccess(){
		ByteBuffer b = ByteBuffer.allocate(500);
		long start = System.currentTimeMillis();
		for(int i=0; i<100000; i++){
			for(int j=0; j<99; j++)
				b.putInt(j);
			b.flip();
			for(int j=0; j<99; j++)
				b.getInt();
			b.clear();
		}
		long end = System.currentTimeMillis();
		System.out.println("heap mem write:"+(end-start));
	}
	
	/**直接内存空间申请耗时*/
	public void directAllocate(){
		long start = System.currentTimeMillis();
		for(int i=0; i<10000; i++){
			ByteBuffer b = ByteBuffer.allocateDirect(1000);
		}
		long end = System.currentTimeMillis();
		System.out.println("direct mem write:"+(end-start));
	}
	
	/**堆内存空间申请耗时*/
	public void heapAllocate(){
		long start = System.currentTimeMillis();
		for(int i=0; i<10000; i++){
			ByteBuffer b = ByteBuffer.allocate(1000);
		}
		long end = System.currentTimeMillis();
		System.out.println("heap mem write:"+(end-start));
	}
}
