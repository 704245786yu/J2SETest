package DesignPattern.Factory.AbstractFactory;

import DesignPattern.Factory.AbstractFactory.bo.Cheese;
import DesignPattern.Factory.AbstractFactory.bo.Clam;
import DesignPattern.Factory.AbstractFactory.bo.Dough;
import DesignPattern.Factory.AbstractFactory.bo.NYCheese;
import DesignPattern.Factory.AbstractFactory.bo.NYClam;
import DesignPattern.Factory.AbstractFactory.bo.NYDough;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		return new NYDough();
	}

	@Override
	public Clam createClam() {
		return new NYClam();
	}

	@Override
	public Cheese createCheese() {
		return new NYCheese();
	}

}
