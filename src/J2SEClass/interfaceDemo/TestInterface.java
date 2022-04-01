package J2SEClass.interfaceDemo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * interface的试验
 */

public class TestInterface {
    public interface ContainInstance{
        List list = new ArrayList();
    }

    @Test
    public void test1(){
        ContainInstance.list.add("a");
//        System.out.println(ContainInstance.a);
        System.out.println(ContainInstance.list);
    }
}
