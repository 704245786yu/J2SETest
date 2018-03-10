package GenericClasses;

import org.junit.Test;

public class TestGenericMethod {

	public static <T> T getMiddle(T... a){
		return a[0];
	}
	
	/**测试泛型方法
	 * */
	@Test
	public void test1(){
		System.out.println(TestGenericMethod.getMiddle(3.14, 1729, 0.0));
	}
}
