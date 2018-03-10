package GenericClasses.wildcard;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

class Stats<T extends Number>{
	T[] nums;
	
	Stats(T[] o){
		nums = o;
	}
	
	//计算数组的平均值
	double average(){
		double sum = 0.0;
		for(int i=0; i<nums.length; i++)
			sum += nums[i].doubleValue();
		return sum/nums.length;
	}
	
	/*判定两个Stats包含的数组的平均值是否一样
	 * Stats<?>表示和所有Stats对象匹配。此处也可写为 Stats<? extends Number>，但却不能写为<T extends Number>或<E extends Number>，为什么目前不清楚。
	 * */
	boolean sameAvg(Stats<?> ob){
		if(average() == ob.average())
			return true;
		return false;
	}
}

public class Test1 {

	/**演示通配符的使用*/
	@Test
	public void test1(){
		Integer inums[] = {1,2,3};
		Stats<Integer> iob = new Stats<>(inums);
		Double dnums[] = {1.0, 2.0, 3.0};
		Stats<Double> dob = new Stats<>(dnums);
		
		if(iob.sameAvg(dob))
			System.out.println("same");
		else
			System.out.println("differ");
	}
	
}
