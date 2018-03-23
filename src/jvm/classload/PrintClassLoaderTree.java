package jvm.classload;

public class PrintClassLoaderTree {

	public static void main(String[] args) {
		ClassLoader cl = PrintClassLoaderTree.class.getClassLoader();
		while(cl != null){
			System.out.println(cl);
			cl = cl.getParent();
		}
		
		System.out.println(String.class.getClassLoader());	//无法在Java代码中访问Bootstrap ClassLoader，该类是由C实现的。
	}
}
