package DesignPattern.Factory.AbstractFactory.bo;

public abstract class Pizza {

	String name;
	Dough dough;
	Clam clam;
	Cheese cheese;
	
	/**在这个方法中收集比萨所需的原料，而这些原料来自原料工厂。不同的Pizza需要不同的原料。*/
	public abstract void prepare();
	
	public void bake(){
		System.out.println("Bake for 25 minutes");
	}
	
	public void cut(){
		System.out.println("Cutting the pizza");
	}
	
	void setName(String name){
		this.name = name;
	}
	
	String getName(){
		return name;
	}
}
