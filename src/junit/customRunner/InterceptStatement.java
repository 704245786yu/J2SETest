package junit.customRunner;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.Statement;

/**
 * @author zhiyu
 * @Date 2020-06-21
 */
public class InterceptStatement extends Statement {
    private final Statement statement;
    private List<Interceptor> interceptors = new ArrayList<>();

    public InterceptStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void evaluate() throws Throwable {
        for (Interceptor interceptor : interceptors){
            interceptor.interceptBefore();
        }
        statement.evaluate();
        for (Interceptor interceptor : interceptors){
            interceptor.interceptAfter();
        }
    }

    public void addInterceptor(Interceptor interceptor){
        interceptors.add(interceptor);
    }
}
