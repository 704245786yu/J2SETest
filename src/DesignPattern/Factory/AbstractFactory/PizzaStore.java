package DesignPattern.Factory.AbstractFactory;

import DesignPattern.Factory.AbstractFactory.bo.Pizza;

public abstract class PizzaStore {

	public Pizza orderPizza(String type){
		Pizza pizza = this.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		return pizza;
	}
	
	/**工厂方法*/
	abstract Pizza createPizza(String type);
}
