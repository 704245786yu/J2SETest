package DesignPattern.Decorate;

/**装饰者模式-测试代码
 * 可以利用”工厂“和“生成器” 模式来更好的建立被装饰者对象。
 * */
public class TestDecorate {
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
