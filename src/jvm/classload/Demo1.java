package jvm.classload;

class Parent{
	static{
		System.out.println("Parent init");
	}
}

class Child extends Parent{
	static{
		System.out.println("Child init");
	}
}

/**演示类的主动使用*/
public class Demo1 {
	public static void main(String[] args) {
		/*打印输出：
		 * Parent init
			Child init
			由此克制，系统首先装载Parent类，接着再装载Child类。符合主动装载中的两个条件：
			1、使用new关键字创建类的实例会装载相关类。
			2、在初始化子类时，必须先初始化父类。
		 * */
		Child c = new Child();
	}
}
