package junit.customRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author zhiyu
 * @Date 2020-06-21
 */
@RunWith(InterceptorRunner.class)
@InterceptorRunner.InterceptorClasses(SampleLoggingInterceptor.class)
public class TestCustomRunnerWithLoggingInterceptor {
    @Before
    public void before(){
        System.out.println("Real before");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @After
    public void after(){
        System.out.println("Real after");
    }
}
