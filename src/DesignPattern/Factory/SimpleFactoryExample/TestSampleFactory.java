package DesignPattern.Factory.SimpleFactoryExample;

import org.junit.Test;

public class TestSampleFactory {

	/**测试简单工厂。
	 * 通过SimplePizzaFactory这个简单工厂创建Pizza的好处是：SimplePizzaFactory可以有许多客户，如这里的PizzaStore.orderPizza()方法，日后可能还有别的客户会使用简单工厂的createPizza()方法。
	 * 所以把创建比萨的代码包装进一个类，当以后实现改变时，只需修改这个类即可。
	 * SimplePizzaFactory的createPizza()方法通常声明为静态。使用静态方法可以不需要使用创建对象的方法来实例化对象。但这也有个缺点，不能通过继承来改变创建方法的行为。
	 * */
	@Test
	public void test1(){
		
	}
}
