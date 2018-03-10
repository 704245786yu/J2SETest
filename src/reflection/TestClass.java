package reflection;

import java.io.FileOutputStream;
import java.io.Flushable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import reflection.javaBean.Bean1;

/**对Class的一些方法进行测试
 * */
public class TestClass {

	/**测试针对不同的类型调用getClass()，所打印的结果*/
	@Test
	public void testGetClass(){
		Integer i = 1;
		System.out.println(i.getClass() == Integer.class);//true，虚拟机为每个类型管理一个Class对象
		System.out.println(i.getClass());	//class java.lang.Integer
		System.out.println(Integer.class);//class java.lang.Integer
		
		Integer[] a = new Integer[10];
		int b[] = new int[10];
		System.out.println(a.getClass());	//class [Ljava.lang.Integer;
		System.out.println(b.getClass());	//class [I
//		System.out.println(a.getClass() == b.getClass());此语句是不能编译通过的，不同类型的对象是不能用==比较的，这里牵扯到泛型
	}
	
	/**根据存储在字符串中的类名创建一个对象*/
	@Test
	public void testNewInstance(){
		String s = "java.util.Random";
		try {
			//通过调用静态方法forName()获得类名对应的Class对象
			//使用newIntance()动态地创建一个类的实例，该方法只能调用默认的无参构造器，要想调用有参构造器，需使用Constructor类中的newInstance()。
			Object o = Class.forName(s).newInstance();
			System.out.println(o);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**测试Class的isArray()方法*/
	@Test
	public void testIsArray(){
		ArrayList<?> ary = new ArrayList<>();
		System.out.println(ary.getClass().isArray());	//false
		int []a = new int[1];
		System.out.println(a.getClass().isArray());	//true
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testGetComponentType(){
		int []a = new int[1];
		Class c = a.getClass().getComponentType();
		System.out.println(c.getName());	//int
		ArrayList<?> ary = new ArrayList<>();
		c = ary.getClass().getComponentType();	//对于不是数组类型的会返回null
		System.out.println(c);	//null
		Integer [] i = new Integer[1];
		c = i.getClass().getComponentType();
		System.out.println(c);	//java.lang.Integer
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testGetSuperclass(){
		Integer[] i = new Integer[1];
		Class c1 = i.getClass();
		Class c2 = c1.getSuperclass();
		System.out.println(c2);	//Object，数组类型的父类是Object（废话！所有类型的父类都是Object）
		System.out.println(c2.getSuperclass());	//null
	}
	
	/**判定有当前的这个Class对象所表示的类或接口，是否与由指定的Class参数所表示的类或接口相同，或者是其父类或父接口。
	 * */
	@Test
	public void testIsAssignableFrom(){
		System.out.println(Flushable.class.isAssignableFrom(FileOutputStream.class));
	}
	
	@Test
	public void testGetDeclaredField(){
		try {
			Field filed = Bean1.class.getDeclaredField("createBy");
		} catch (NoSuchFieldException e) {
			System.out.println("no such createBy");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**Class.getComponentType()只针对数组，不针对集合*/
	@Test
	public void test_Class_getComponentType(){
		List<Integer> list = new ArrayList<>();
		System.out.println(list.getClass().getComponentType());//打印null
	}
	
	/**获取类所在包名*/
	@Test
	public void test_Class_getPackage(){
		String name = TestClass.class.getPackage().getName();
		System.out.println(name);
	}
	
	@Test
	public void testGetMethod(){
		class User{
			private String name;
			public String getName() {
				return name;
			}
		}
		Object user = new User();
		Class c = user.getClass();
		try {
			Method m = c.getMethod("getName");
			System.out.println(m);
			m.invoke(user);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
