package DesignPattern.Factory.AbstractFactory.bo;

import DesignPattern.Factory.AbstractFactory.PizzaIngredientFactory;

/**蛤蜊比萨*/
public class ClamPizza extends Pizza {

	/**不同风味的比萨，不需要像FactoryMethodExample包中那样，设计两个不同的CheezePizza子类来处理，让原料工厂处理这种区域差异就可以了*/
	PizzaIngredientFactory ingredientFactory;
	
	public ClamPizza(PizzaIngredientFactory ingredientFactory){
		this.ingredientFactory = ingredientFactory;
	}
	
	@Override
	public void prepare() {
		System.out.println("Preparing "+name);
		//通过原料工厂获取制作比萨所需的原料
		dough = ingredientFactory.createDough();
		clam = ingredientFactory.createClam();
	}

}
