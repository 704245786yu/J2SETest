package DesignPattern.Factory.AbstractFactory;

import DesignPattern.Factory.AbstractFactory.bo.Cheese;
import DesignPattern.Factory.AbstractFactory.bo.Clam;
import DesignPattern.Factory.AbstractFactory.bo.Dough;

/**原料工厂，用于创建制作比萨的各种不同的原料*/
public interface PizzaIngredientFactory {

	/**面团*/
	public Dough createDough();
	/**芝士*/
	public Cheese createCheese();
	/**蛤蜊*/
	public Clam createClam();
}
