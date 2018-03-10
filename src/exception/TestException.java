package exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.junit.Test;

public class TestException {

	/************************************获取堆栈轨迹（stack trace），堆栈轨迹是一个方法调用过程的列表*************************************************/
	@Test
	public void testStackTrace(){
		Throwable t = new Throwable();
		StringWriter out = new StringWriter();
		t.printStackTrace(new PrintWriter(out));
		String desc = out.toString();
		System.out.println(desc);
	}
	
	@Test
	public void testStackTraceElement(){
		Throwable t = new Throwable();
		StackTraceElement[] frames = t.getStackTrace();
		for(StackTraceElement frame : frames){
			System.out.println("className："+frame.getClassName()+", fileName："+frame.getFileName()+", lineNumber："+frame.getLineNumber()+", methodName："+frame.getMethodName());
			System.out.println(frame.toString());
		}
	}
	
	@Test
	public void testGetAllStackTraces(){
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();//该方法可以产生所有线程的堆栈轨迹
		for(Thread t : map.keySet()){
			StackTraceElement[] frames = map.get(t);
			System.out.println("线程:"+t.getName()+"-"+t.getId());
			for(StackTraceElement ste : frames)
				System.out.println(ste);
		}
	}
	
	/**阶乘*/
	private static int factorial(int n){
		System.out.println("factorial("+n+"):");
		Throwable t = new Throwable();
		StackTraceElement[] frames = t.getStackTrace();
		for(StackTraceElement frame : frames)
			System.out.println(frame);
		int r;
		if(n<=1)
			r = 1;
		else
			r = n * factorial(n-1);
		System.out.println("return "+r);
		return r;
	}
	
	/**打印递归阶乘函数的堆栈情况*/
	@Test
	public void testFactorial(){
		factorial(3);
	}
	/**打印递归阶乘函数的堆栈情况，main打印出来的信息与JUnit不一样
	 * 
	 * */
	public static void main(String[] args){
		factorial(3);
	}
	
}
