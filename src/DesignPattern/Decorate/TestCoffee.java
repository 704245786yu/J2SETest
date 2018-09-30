package DesignPattern.Decorate;

/**测试代码*/
public class TestCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresson();
        System.out.println(beverage.getDescription()+" $"+beverage.cost());

        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Soy(beverage1);
        System.out.println(beverage1.getDescription()+" $"+beverage1.cost());
    }
}
