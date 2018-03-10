package GenericClasses.BridgeMethod;

import java.time.LocalDate;

public class DateInterval extends Pair<LocalDate> {

//	@Override
	public void setSecond(LocalDate second){
		System.out.println("DateInterval setSecond()");
		if(second.compareTo(getFirst()) >=0 )
			super.setSecond(second);
	}
	
	/**DateInterval还有一个从父类继承的Object getSecond()方法。此处之所以不会报错，是因为在虚拟机中，用参数类型和返回类型确定一个方法。但在Java代码中是不允许这样的。
	 * */
	public LocalDate getSecond(){
		return null;
	}
}
