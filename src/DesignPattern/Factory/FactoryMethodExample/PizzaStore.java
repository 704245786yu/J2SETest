package DesignPattern.Factory.FactoryMethodExample;

import DesignPattern.Factory.FactoryMethodExample.bo.Pizza;

public abstract class PizzaStore {

	public Pizza orderPizza(String type){
		Pizza pizza = this.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
	
	/**工厂方法*/
	abstract Pizza createPizza(String type);
}
