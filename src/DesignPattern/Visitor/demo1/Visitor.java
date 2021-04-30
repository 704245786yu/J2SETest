package DesignPattern.Visitor.demo1;

/**访问者
 * @author zhiyu
 * @Date 2020-02-19
 */
public interface Visitor {
    /**该方法用来实现对Element接口的实例要进行的处理的*/
    void visit(Element element);
}
