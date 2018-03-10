package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class ReflectionTest {

	/**打印一个类的全部信息的方法
	 * 输出类中所有方法和构造器的签名，以及全部域名
	 * */
	@SuppressWarnings("rawtypes")
	@Test
	public void test(){
//		String className = "java.lang.Double";
//		String className = "java.util.Comparator";
		String className = "reflection.javaBean.Bean1";
		//如果不是Object类型，打印class name和superclass name
		try {
			Class c1 = Class.forName(className);
			//返回由Class的实体（类、接口、基本类型、void）的超类的Class。如果这个Class标识Object类，接口，原始类型，或void，则返回null。
			//如果此对象表示数组类型，则返回表示Object类的Class对象。
			Class superC1 = c1.getSuperclass();
			//获取类的访问修饰符，并用Modifier.toString()打印出来
			String modifiers = Modifier.toString(c1.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.print("class "+className);
			if(superC1 != null && superC1 != Object.class)
				System.out.print(" extends "+superC1.getName());
			
			System.out.print("\n{\n");
			printConstructors(c1);
			System.out.println();
			printMethods(c1);
			System.out.println();
			printFields(c1);
			System.out.println("}");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**打印类的所有构造方法*/
	@SuppressWarnings("rawtypes")
	public void printConstructors(Class c1){
		//返回类的所有声明的构造函数的Constructor对象的数组，包含了类中声明的全部域的构造方法，但不包括超类的成员。
		Constructor[] constructors = c1.getDeclaredConstructors();
		for(Constructor c : constructors){
			//返回构造函数的名称。这是构造函数的声明类的二进制名称，即全类名
			String name = c.getName();
			System.out.print(" ");
			//获取构造方法的访问修饰符
			String modifiers = Modifier.toString(c.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.print(name+"(");
			
			//打印参数类型
			//按声明的顺序返回参数类型数组
			Class[] paramTypes = c.getParameterTypes();
			for(int j=0; j<paramTypes.length; j++){
				if(j>0)
					System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	
	/**打印类的所有方法*/
	@SuppressWarnings("rawtypes")
	public void printMethods(Class c1){
		//返回类或接口的所有声明的方法的数组，包含了全部域的方法，但不包括继承的方法。
		Method[] methods = c1.getDeclaredMethods();
		for(Method m : methods){
			//获取Method对象所表示的方法的返回值类型所表示的Class对象
			Class returnType = m.getReturnType();
			//返回方法的名称
			String name = m.getName();
			System.out.print("  ");
			//打印访问修饰符，返回类型 和 方法名
			String modifiers = Modifier.toString(m.getModifiers());
			if(modifiers.length() > 0)
				System.out.print(modifiers+" ");
			System.out.print(returnType.getName()+" "+name+"(");
			//打印参数类型
			Class[] paramTypes = m.getParameterTypes();
			for(int j=0; j<paramTypes.length; j++){
				if(j>0)
					System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	
	/**打印类的所有字段*/
	@SuppressWarnings("rawtypes")
	public void printFields(Class c1){
		//返回类或接口声明的所有字段，包括全部域的方法，但不包括继承的字段。返回的元素无序。
		Field[] fields = c1.getDeclaredFields();
		for(Field f : fields){
			//返回字段的声明类型
			System.out.println("===="+f.getDeclaringClass());
			Class type = f.getType();
			//返回字段的名称
			String name = f.getName();
			System.out.print("  ");
			String modifiers = Modifier.toString(f.getModifiers());
			if(modifiers.length() > 0)
				System.out.print(modifiers+" ");
			System.out.println(type.getName()+" "+name+";");
		}
	}
}
