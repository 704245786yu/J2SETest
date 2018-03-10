package DesignPattern.Factory.FactoryMethodExample.bo;

public abstract class Pizza {

	/**Pizza准备*/
	public abstract void  prepare();
	/**烘焙*/
	public abstract void bake();
	/**切*/
	public abstract void cut();
	/**装盒*/
	public abstract void box();
}
