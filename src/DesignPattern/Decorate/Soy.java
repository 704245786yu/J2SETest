package DesignPattern.Decorate;

/**豆浆*/
public class Soy extends CondimentDecorator{
    /**保存被装饰者*/
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return 0.1 + beverage.cost();
    }
}
