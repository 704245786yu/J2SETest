package DesignPattern.Factory.FactoryMethodExample;

import DesignPattern.Factory.FactoryMethodExample.bo.NYStyleCheesePizza;
import DesignPattern.Factory.FactoryMethodExample.bo.NYStyleClamPizza;
import DesignPattern.Factory.FactoryMethodExample.bo.Pizza;

public class NYStylePizzaStore extends PizzaStore {

	@Override
	Pizza createPizza(String type) {
		Pizza pizza = null;
		if(type.equals("cheese")){
			pizza = new NYStyleCheesePizza();
		}else if(type.equals("cheese")){
			pizza = new NYStyleClamPizza();
		}
		return pizza;
	}

}
