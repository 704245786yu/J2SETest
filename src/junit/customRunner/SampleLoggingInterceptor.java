package junit.customRunner;

/**
 * @author zhiyu
 * @Date 2020-06-21
 */
public class SampleLoggingInterceptor implements Interceptor {
    @Override
    public void interceptBefore() {
        System.out.println("Before-test");
    }

    @Override
    public void interceptAfter() {
        System.out.println("After-test");
    }
}
