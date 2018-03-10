package DesignPattern.Factory.SimpleFactoryExample;

import DesignPattern.Factory.SimpleFactoryExample.bo.Pizza;

public class PizzaStore {

	SimplePizzaFactory factory;
	
	public PizzaStore(SimplePizzaFactory factory){
		this.factory = factory;
	}
	
	public Pizza orderPizza(String type){
		Pizza pizza = factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
