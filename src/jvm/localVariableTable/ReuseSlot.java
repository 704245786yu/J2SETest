package jvm.localVariableTable;

/**演示栈帧中的局部变量表的槽位是可重用的，利用 jclasslib查看局部变量表的槽位使用情况*/
public class ReuseSlot {

	public void localVar1(){
		int a=0;
		System.out.println(a);
		int b=0;
	}
	
	public void localVar2(){
		{
			int a=0;
			System.out.println(a);
		}
		int b=0;
	}
}
