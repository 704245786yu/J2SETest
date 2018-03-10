package DesignPattern.Factory.FactoryMethodExample;

import org.junit.Test;

public class Test1 {

	/**实例化Pizza的责任被移到一个“方法”中，此方法就如同是一个“工厂”。
	 * 工厂方法用来处理对象的创建，并将这样的行为封装在子类中。
	 * 工厂方法模式（Factory Method Pattern）通过让子类决定该创建的对象是什么，来达到将对象创建的过程封装的目的。
	 * 工厂方法模式与简单工厂的区别是，简单工厂把全部的事情，在一个地方都处理完了。而工厂方法却是创建一个框架，让子类决定要如何实现。如，在工厂方法中，
	 * orderPizza()方法提供了一般的框架，可对创建后的比萨做一些别的操作，而该方法依赖工厂方法创建具体类。简单工厂的做法，可以将对象的创建封装起来，
	 * 但它不具备工厂方法的弹性，因为它不能变更正在创建的产品。
	 * */
	@Test
	public void test1(){
		PizzaStore nyPizzaStore = new NYStylePizzaStore();
		nyPizzaStore.orderPizza("cheese");
	}
}
