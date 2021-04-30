package DesignPattern.Decorate;

/**装饰者类
 * 调料-摩卡*/
public class Mocha extends CondimentDecorator{
    /**保存被装饰者，该实例变量也可放在CondimentDecorator抽象类里*/
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        //此处利用委托的做法得到一个叙述，然后在其后加上附加的叙述。
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        //要计算带Mocha饮料的价钱，首先把调用委托给被装饰对象，以计算价钱，然后再加上Mocha的价钱。
        return 0.2 + beverage.cost();
    }
}
