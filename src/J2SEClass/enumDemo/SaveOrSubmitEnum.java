package J2SEClass.enumDemo;

/**
 * @author zhiyu
 * @Date 2019-08-28
 */
public enum SaveOrSubmitEnum {
    SAVE(1),//保存
    SUBMIT(2);//提交

    public int code;    //枚举类型里的域是可以改变的，应该改为public final

    private SaveOrSubmitEnum(int code) {
        this.code = code;
    }
}
