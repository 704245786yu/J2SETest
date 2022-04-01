package J2SEClass;

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
	public void IntegerMax(){
		System.out.println(Integer.MAX_VALUE+" "+(Integer.MAX_VALUE+1));
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
    public void test9(){
        System.out.println(BigDecimal.ZERO);
    }

    @Test
    public void test10(){
        System.out.println(Math.random());
        System.out.println(Math.random()*9);
        System.out.println(Math.random()*9+1);
        int i = (int)((Math.random()*9+1)*100000);
        System.out.println(i);
    }

    @Test
	public void testBitOperator(){
		System.out.println( (1<<0) + " " + Integer.toBinaryString(1<<0));
		System.out.println( (1<<1) + " " + Integer.toBinaryString(1<<1));
		System.out.println( (1<<2) + " " + Integer.toBinaryString(1<<2));
		System.out.println( (1<<3) + " " + Integer.toBinaryString(1<<3));
		System.out.println( (1<<4) + " " + Integer.toBinaryString(1<<4));
	}
}
