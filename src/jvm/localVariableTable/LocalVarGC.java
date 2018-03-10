package jvm.localVariableTable;

/**演示局部变量对垃圾回收的影响
 * -XX:+PrintGC，输出垃圾回收日志
 * */
public class LocalVarGC {

	public void localVarGc1(){
		byte[] a = new byte[6*1024*1024];
		System.gc();	//由于byte[]被变量a引用，因此无法回收这块空间
	}
	
	public void localVarGc2(){
		byte[] a = new byte[6*1024*1024];
		a=null;
		System.gc();//byte[]失去强引用，所以会被垃圾回收
	}
	
	public void localVarGc3(){
		{
			byte[] a = new byte[6*1024*1024];
		}
		System.gc();	//在进行垃圾回收前，先使局部变量a失效，虽然变量a已经离开了作用域，但a依然存在于局部变量表中，并且也指向byte[]，故byte[]依然无法被回收
	}
	
	public void localVarGc4(){
		{
			byte[] a = new byte[6*1024*1024];
		}
		int c=10;
		//与localVarGc3()类似，a失效，但申明了变量c，使c复用了变量a的字（即局部变量表的槽位）,由于a此时被销毁，所以byte[]被回收
		//另外，垃圾回收是从根节点开始的，而局部变量表中的变量是其中一种根节点
		System.gc();
	}
	
	public void localVarGc5(){
		localVarGc1();
		System.gc();	//localVarGc1()返回后，它的栈帧被销毁，其中就包含了所有的局部变量，故byte[]失去引用，在本方法中被回收。
	}
	
	public static void main(String[] args) {
		LocalVarGC localVarGc = new LocalVarGC();
//		localVarGc.localVarGc1();
//		localVarGc.localVarGc2();
//		localVarGc.localVarGc3();
		localVarGc.localVarGc4();
//		localVarGc.localVarGc5();
	}
}
