package J2SEClass.enumDemo;

/**
 * @author zhiyu
 * @Date 2019-08-28
 */
public class demo2 {

    public static void main(String[] args) {
        System.out.println(SaveOrSubmitEnum.SAVE.code);
        SaveOrSubmitEnum.SAVE.code = 2; //枚举类型里的域是可以改变的，应该改为public final
        System.out.println(SaveOrSubmitEnum.SAVE.code);
        System.out.println(SaveOrSubmitEnum.SUBMIT.code);
    }

}