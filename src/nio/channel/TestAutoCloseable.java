package nio.channel;

import org.junit.Test;

/** java.nio.channels.Channel继承了java.io.Closeable，Closeable继承了java.lang.AutoCloseable
 * AutoCloseable的作用是可以自动关闭，而不需要显示地调用close()。
 * @author zhiyu
 * @Date 2020-07-23
 */
public class TestAutoCloseable {
    @Test
    public void test(){
        //如果try后的小括号中有多条语句，则最后一条是没有分号的，并且括号中的变量都要实现AutoCloseable接口。
        try(Operate operate = new Operate()) {
            System.out.println("使用"+operate+"开始数据操作");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**AutoCloseable强调的是与try()结合实现自动关闭，该接口针对的是任何资源，不仅仅是IO，因此close()方法抛出Exception异常。
 * 该接口不要求是幂等的，也就是重复调用此接口close()会出现副作用。
 * */
class Operate implements AutoCloseable{

    @Override
    public void close() throws Exception {
        System.out.println("关闭连接");
    }
}