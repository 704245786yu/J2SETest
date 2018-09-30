package DesignPattern.Decorate;

import DesignPattern.Decorate.Beverage;

/**浓缩咖啡*/
public class Espresson extends Beverage {
    public Espresson() {
        description = "Espresso 浓缩咖啡";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
