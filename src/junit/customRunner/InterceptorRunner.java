package junit.customRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**自定义JUnit运行器
 * @author zhiyu
 * @Date 2020-06-21
 */
public class InterceptorRunner extends BlockJUnit4ClassRunner {

    /**该注解用于保存junit.customRunner.Interceptor接口的实现*/
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface InterceptorClasses{
        public Class<?>[] value();
    }

    public InterceptorRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    /**@param method 测试方法
     * @param method 测试类
     * */
    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        Statement statement = super.methodInvoker(method, test);
        InterceptStatement interceptStatement = new InterceptStatement(statement);
        InterceptorClasses interceptorClasses = test.getClass().getAnnotation(InterceptorClasses.class);
        Class<?>[] classes = interceptorClasses.value();
        try {
            for (Class<?> clazz : classes){
                interceptStatement.addInterceptor( (Interceptor) clazz.newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return interceptStatement;
    }
}
