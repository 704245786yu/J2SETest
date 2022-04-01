package J2SEClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;

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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHH:mm:ssSSS");
		LocalDateTime dateTime = LocalDateTime.parse("2018121418:38:53111",dtf);
		System.out.println(dateTime);
	}

    @Test
    public void formatter2(){
	    String s = "20200205182509123";
	    s = s.substring(0,s.length()-3);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(s,dtf);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strDate2 = dtf2.format(dateTime);
        System.out.println(strDate2);
        Date date = Date.from( dateTime.atZone( ZoneId.systemDefault()).toInstant());
        System.out.println(date);
    }
}
