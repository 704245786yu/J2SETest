package reflection;

import java.lang.reflect.Field;

import org.junit.Test;

import reflection.javaBean.Bean1;

public class TestField {

	/**测试Field.get()、setAccessible()方法*/
	@SuppressWarnings("rawtypes")
	@Test
	public void testGet(){
		Bean1 b = new Bean1(1.0);
		Class c1 = b.getClass();
		Field f = null;
		try {
			f = c1.getDeclaredField("d");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			Object v = f.get(b);//返回b对象的f字段的值，此处会抛异常，因为Java安全机制不允许对读取private字段的值
			System.out.println(v);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			System.out.println("使用Field的get()方法访问private字段，会抛异常");
			e1.printStackTrace();
		}
		
		//修改private字段为可访问的
		f.setAccessible(true);
		try {
			Object v = f.get(b);//由于使用了Field的setAccessible()方法修改可访问性，所以可以获取数据
			System.out.println(v);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**调用Field.set()方法，给Field设置新值*/
	@SuppressWarnings("rawtypes")
	@Test
	public void testSet(){
		Bean1 b = new Bean1(1.0);
		Class c1 = b.getClass();
		Field f = null;
		try {
			f = c1.getDeclaredField("d");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			f.set(b, 2.0);//设置b对象的f字段的值，此处会抛异常，因为Java安全机制不允许对访问private字段
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//修改private字段为可访问的
		f.setAccessible(true);
		try {
			f.set(b, 2.0);//由于使用了Field的setAccessible()方法修改可访问性，所以可以设置值
			System.out.println(b.getD());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**======测试AccessiableObject.setAccessible()是否永久影响，是仅对当前对象影响，还是对类有影响======*/
	/**通过此测试发现，通过对象获取的Field，修改了其accessible标志，不会对该类的其他对象的这个Field产生影响
	 * */
	@Test
	public void testSetAccessible1(){
		Bean1 b = new Bean1();
		try {
			Field f1 = b.getClass().getDeclaredField("d");
			f1.setAccessible(true);
			try {
				System.out.println(f1.get(b));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		Bean1 b2 = new Bean1();
		try {
			Field f1 = b2.getClass().getDeclaredField("d");
			try {
				System.out.println(f1.get(b2));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**通过此测试发现，直接对类的Field设置accessible标志，并不会影响实体对象的Field的可访问性*/
	@Test
	public void testSetAccessible2(){
		Field f=null;
		try {
			f = Bean1.class.getDeclaredField("d");
			f.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		}
		
		Bean1 b = new Bean1(2.0);
		try {
			Field f1 = b.getClass().getDeclaredField("d");
			try {
				System.out.println(f1.get(b));	//会抛异常
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**将此测试的结果与testSetAccessible2()的测试对比发现，
	 * 其实Field是从哪里获取的并不重要，调用setAccessiable()只会对当前的这个Field对象有影响，而不是对Field所表示的字段本身的访问性产生影响*/
	@Test
	public void testSetAccessible3(){
		Field f=null;
		try {
			f = Bean1.class.getDeclaredField("d");
			f.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		}
		
		Bean1 b = new Bean1(2.0);
		try {
			System.out.println(f.get(b));	//输出2.0
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}