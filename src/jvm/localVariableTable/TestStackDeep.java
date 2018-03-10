package jvm.localVariableTable;

/**测试栈深度，并利用 jclasslib查看局部变量表
 * -Xss128K 限制栈大小
 * */
public class TestStackDeep {

	private static int count = 0;
	
	//如果方法没有static修饰符，则局部变量表还会在下标0槽位包含函数的this引用。
	public static void recursion(long a, long b, long c){
		long e=1, f=2, g=3, h=4, i=5, j=6, k=7, l=8, m=9, n=10;
		count++;
		recursion(a,b,c);
	}
	
	public static void recursion(){
		count++;
		recursion();
	}
	
	public static void main(String args[]){
		try{
//			recursion(0L, 0L, 0L);
			recursion();
		}catch(Throwable e){
			System.out.println("deep of calling = "+count);
			e.printStackTrace();
		}
	}
}
