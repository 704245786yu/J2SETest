package J2SEClass.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    @Test
    public void test1(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.stream().forEach(num->System.out.println(Thread.currentThread().getName()+", num:"+num));
        System.out.println("==============");
        numbers.parallelStream().forEach(num->System.out.println(Thread.currentThread().getName()+", num:"+num));
    }
}
