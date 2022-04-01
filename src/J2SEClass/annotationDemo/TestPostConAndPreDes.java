package J2SEClass.annotationDemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**测试@PostConstruct和@PreDestroy。这两个注解只能用在容器中，在此无任何效果。
 * @author zhiyu
 * @since 2018-12-27
 */
public class TestPostConAndPreDes {
    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}
class Demo{
    public Demo(){
        System.out.println("Demo()");
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println("postConstruct()被调用");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("postConstruct()被调用");
    }
}