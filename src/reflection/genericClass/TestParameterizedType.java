package reflection.genericClass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import reflection.TestClass;

/**测试ParameterizedType接口用法
 * */
public class TestParameterizedType {

	/**注意，ParameterizedType.getActualTypeArguments()的所谓获取实际参数类型并不是指运行时类型，而是代码里定义的类型，即编译时可以获取的，
	 * 如在代码里定义了Map<Integer,String>，才可以通过该方法获取Integer和String。
	 * */
	@Test
	public void test_method_getActualTypeArguments(){
		List<Integer> list = new ArrayList<>();
		Class<?> c = list.getClass();
		Type superClassType = c.getGenericSuperclass();
		ParameterizedType type = (ParameterizedType)superClassType;
		Type[] typeArgs = type.getActualTypeArguments();
		System.out.println(typeArgs[0]);//打印：E
	}
	
	/**获取类所在包名*/
	@Test
	public void test_Class_getPackage(){
		String name = TestParameterizedType.class.getPackage().getName();
		System.out.println(name);
	}
}
