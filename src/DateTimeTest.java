import java.time.LocalDate;

import org.junit.Test;

/**测试JDK 8日期API
 * */
public class DateTimeTest {

	//打印七天前日期
	@Test
	public void test1(){
		LocalDate date = LocalDate.now();
		LocalDate date1 = date.minusDays(6);
		System.out.println(date1);
	}
}
