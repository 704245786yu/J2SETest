package DesignPattern.Decorate;

/**装饰者类-调料抽象类*/
public abstract class CondimentDecorator extends Beverage{
    //所有的调料装饰者都必须重新实现该方法，这是因为...
    public abstract String getDescription();
}
