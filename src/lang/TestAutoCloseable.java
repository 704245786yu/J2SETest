package lang;

/**AutoCloseable接口的作用是可以自动关闭，而不需要显示地调用close()。
 * AutoCloseable强调的是与try()结合实现自动关闭，该接口针对的是任何资源，不仅仅是IO，
 * 因此close()方法设计为抛出Exception异常。
 * 该接口不要求是幂等的，即重复调用此接口的close()会出现副作用。
 * @author zhiyu
 * @Date 2019-11-12
 */
public class TestAutoCloseable{

    public static void main(String[] args) {
        //如果try后的小括号里有多条语句，最后一条需是没有分号的
        //并且小括号中的变量都要实现AutoCloseable接口
        try(AutoCloseableImpl autoCloseable = new AutoCloseableImpl()){
            System.out.println("使用"+autoCloseable+"执行一些操作");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class AutoCloseableImpl implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("关闭连接");
    }
}