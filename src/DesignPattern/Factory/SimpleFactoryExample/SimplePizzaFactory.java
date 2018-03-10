package DesignPattern.Factory.SimpleFactoryExample;

import DesignPattern.Factory.SimpleFactoryExample.bo.CheesePizza;
import DesignPattern.Factory.SimpleFactoryExample.bo.ClamPizza;
import DesignPattern.Factory.SimpleFactoryExample.bo.Pizza;

/**简单工厂*/
public class SimplePizzaFactory {

	public Pizza createPizza(String type){
		Pizza pizza = null;
		if(type.equals("cheese")){
			pizza = new CheesePizza();
		}else if(type.equals("cheese")){
			pizza = new ClamPizza();
		}
		return pizza;
	}
}
