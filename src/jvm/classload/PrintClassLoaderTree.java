package jvm.classload;

public class PrintClassLoaderTree {

	public static void main(String[] args) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		System.out.println("current loader: "+classLoader);
		System.out.println("parent loader: "+classLoader.getParent());
		System.out.println("grandparent loader: "+classLoader.getParent().getParent());

		ClassLoader cl = PrintClassLoaderTree.class.getClassLoader();
		while(cl != null){
			System.out.println(cl);
			cl = cl.getParent();
		}
		
		System.out.println(String.class.getClassLoader());	//无法在Java代码中访问Bootstrap ClassLoader，该类是由C实现的。
	}
}
