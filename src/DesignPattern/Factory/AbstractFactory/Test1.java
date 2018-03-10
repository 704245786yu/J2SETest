package DesignPattern.Factory.AbstractFactory;

import org.junit.Test;

public class Test1 {

	/**抽象工厂例子*/
	@Test
	public void test1(){
		PizzaStore nyPizzaStore = new NYPizzaStore();
		nyPizzaStore.orderPizza("cheese");
	}
}
