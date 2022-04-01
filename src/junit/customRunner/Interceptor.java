package junit.customRunner;

/**
 * @author zhiyu
 * @Date 2020-06-21
 */
public interface Interceptor {
    //拦截器方法不接受任何参数。实现拦截器模式的常见方法是以某种上下文对象调用拦截器方法，
    //从而使得这些方法能够监控和访问我们的应用程序。
    void interceptBefore();
    void interceptAfter();
}
