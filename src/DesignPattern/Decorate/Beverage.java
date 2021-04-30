package DesignPattern.Decorate;

/** 饮料(beverage /'bev(ə)rɪdʒ/)抽象类
 * 该类作为要被装饰的对象的抽象类。
 * */
public abstract class Beverage {

    /**用来描述饮料*/
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    /**返回饮料的价钱*/
    public abstract double cost();
}
