package jvm.classload;

class Parent1{
	static{
		System.out.println("Parent init");
	}
	public static int value = 100;
}

class Child1 extends Parent1{
	static{
		System.out.println("Child init");
	}
}

class FinalFieldClass{
	public static final String constString="CONST";
	static{
		System.out.println("FinalFieldClass init");
	}
}

/**演示类的被动使用，被动引用不会导致类的装载。
 * */
public class Demo2 {
	public static void main(String[] args) {
		/*打印输出：
		 * Parent init
			100
			CONST
			由打印可知，在引用类的静态字段时，只有直接定义该字段的类，才会被初始化。
			虽然Child类没有被初始化，但此时Child类已经被系统加载，只是没有进入到初始化阶段。
			另外，引用final静态常量并不会引起类的初始化，并且FinalFieldClass类也不会被加载到系统中。
			这是因为在Class文件生成时，final常量由于其不变性，做了适当的优化，javac在编译时将final常量直接植入目标类（也就是存放到常量池中），不再使用被引用类。
		 * */
		System.out.println(Child1.value);
		System.out.println(FinalFieldClass.constString);
	}
}
