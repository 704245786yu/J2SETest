import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	/**日期时间格式化*/
	@Test
	public void formatter1(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
		LocalDateTime dateTime = LocalDateTime.parse("2018-12-14T18:38:53Z",dtf);
		System.out.println(dateTime);
	}
}
