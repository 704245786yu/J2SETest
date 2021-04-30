package DesignPattern.Visitor.demo1;

/**Element接口是接受访问者访问的接口。继承了该抽象类的类，都能够接受访问者的访问。
 * （Element元素；要素）
 * @author zhiyu
 * @Date 2020-02-19
 */
public abstract class Element {
    /**接受访问者访问*/
    public abstract void accept(Visitor visitor);
}
