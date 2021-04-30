import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

public class NumberTest {

	/**int转成float可能会有精度丢失一定的精度，因为int所包含的位数比float所能够表达的位数多
	 * */
	@Test
	public void test1(){
		int i = 123456789;
		float f = i;
		System.out.println(f);//1.23456792E8
	}

	@Test
	public void test2(){
		ArrayList<String> a = new ArrayList<>();
		String[] s = (String[])test3();
		System.out.println(s);
	}
	
	public Object[] test3(){
		String[] s = new String[]{};
		return s;
	}
	
	@Test
	public void test4(){
		Integer a = 2;
		Integer b = 2;
		Integer c = new Integer(2);
		int d = 2;
		System.out.println(a==b);	//true
		System.out.println(a.equals(b));	//true
		System.out.println(a==c);	//false
		System.out.println(a.equals(c));	//true
		System.out.println(a==d);	//true
	}
	
	@Test
	public void test5(){
		Boolean a = null;
		if(4>3 && a)	//此处会报NullPointerException
			System.out.println();
	}
	
	@Test
	public void test6(){
		Integer a = 2;
		Integer b = a;
		b=3;
		System.out.println(a);
	}
	
	@Test
	public void test7(){
		BigDecimal a = new BigDecimal("0.0");
		BigDecimal b = new BigDecimal("0.00");
		System.out.println(a.equals(b));
		
		a = new BigDecimal("3.5");
		System.out.println(a.subtract(null));//NullPointerExceptioin
	}
	
	@Test
	public void test8(){
		Integer.valueOf(null);
	}

}
