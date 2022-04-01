package J2SEClass.enumDemo;

import org.junit.Test;

public class demo1 {
    enum Size{ S,M,L,X}  //一个内部类

    public static void main(String[] args) {
        Size s = Size.M;
        System.out.println(s);
        Size s1 = Enum.valueOf(Size.class,"S");//根据枚举类型和已定义的常量名称，返回枚举常量。
        System.out.println(s1+" "+s1.ordinal());

    }

    @Test
    public void test1(){
        System.out.println(HospitalLevelEnum.getMsgByCode(1));
    }
}
