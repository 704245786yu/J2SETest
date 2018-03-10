package jvm;

/**测试抛出StackOverflowError
 * VM Args: -Xss128k
 * -Xss：设置栈内存容量
 * */
public class TestStackSOF {

	private int stackLength = 1;//用于记录栈长度
	
	public void stackLeak(){
		stackLength++;
		stackLeak();	//递归太深会发生栈溢出
	}
	
	public static void main(String[] args) {
		TestStackSOF sof = new TestStackSOF();
		try{
			sof.stackLeak();
		}catch(Error e){
			System.out.println("stack length:"+sof.stackLength);
			e.printStackTrace();
		}
	}
}
