package J2SEClass.assertDemo;

import org.junit.Test;

/**Java语言中给出了3种处理系统错误的机制：抛出一个异常、日志、使用断言。
 * <b>断言检查只用于开发和测试阶段，因为断言失败是致命的、不可恢复的错误。</b>
 * 默认情况下断言assert被禁用，需使用JVM参数-enableassertions或-ea选项启用。
 * @author zhiyu
 * @Date 2020-10-16
 */
public class Test1 {

    @Test
    public void test(){
        int a = 1;
        //断言assert机制允许在测试期间向代码中插入一些检查语句，当代码发布时，这些插入的检测语句会被自动移走。
        assert a>1 : "警告：a>1 了";
        System.out.println(++a);
    }
}
