package GenericClasses.BridgeMethod;

import java.time.LocalDate;

import org.junit.Test;

public class Test1 {

	/**泛型引起的桥方法
	 * 此方法运行后会输出：
	 * DateInterval setSecond()
	 * Pair setSecond()
	 * 按照理解，变量pair是无法调用到DateInterval.setSecond(LocalDate)方法，父类引用无法调用子类自己的方法，
	 * pair引用的对象应该只能调用从父类继承的类型擦除方法Pair.setSecond(Object)，此处调用会发生多态。为了能正确调用DateInterval.setSecond(LocalDate),
	 * 编译器会生成一个桥方法。
	 * 
	 * */
	@Test
	public void test1(){
		DateInterval interval = new DateInterval();
		interval.setFirst(LocalDate.now());
		Pair<LocalDate> pair = interval;
		pair.setSecond(LocalDate.now());
	}
}
