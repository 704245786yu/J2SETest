package reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

import reflection.javaBean.Bean1;

/**可供任意类使用的通用toString方法
 * 此功能不提供对继承的字段的访问
 * */
public class ObjectAnalyzer {
	private ArrayList<Object> visited = new ArrayList<>();//防止出现循环引用（A包含了B，而B又包含了A）而导致无限递归，通过此变量记录已被访问过的对象

	//问题：调用了Field的setAccessible后，是否会发生永久性影响
	
	@SuppressWarnings("rawtypes")
	public String toString(Object obj) {
		if(obj==null)
			return "null";
		if(visited.contains(obj))
			return "...";
		visited.add(obj);
		Class c1 = obj.getClass();
		if(c1 == String.class)
			return (String)obj;
		
		if(c1.isArray()){	//判断Class对象是否一个数组类
			String r = c1.getComponentType() + "[]{";	//获取数组类的组件类型的Class
			for(int i=0; i<Array.getLength(obj); i++){	//通过Array.getLength()获取数组的长度
				if(i>0)
					r += ",";
				Object val = Array.get(obj, i);	//返回i下标的值
				if(c1.getComponentType().isPrimitive())	//isPrimitive()判断Class是否表示为原始类型
					r+=val;
				else
					r += this.toString(val);//对于非原始类型的对象，递归调用ObjectAnalyzer.toString()
			}
			return r+"}";
		}
		
		String r = c1.getName();
		//检查类以及所有超类的fields
		do{
			r +="[";
			Field[] fields = c1.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);	//设置所有的Field为可访问的
			//获取所有fields的名称和值
			for(Field f: fields){
				//判断是否static字段
				if( ! Modifier.isStatic(f.getModifiers())){
					if( ! r.endsWith("["))	//测试字符串是否以指定的后缀结尾
						r += ",";
					r += f.getName() + "=";
					Class t = f.getType();
					try {
						Object val = f.get(obj);
						if(t.isPrimitive())
							r += val;
						else
							r += this.toString(val);	//非基本类型对象继续调用ObjectAnalyzer.toString();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			r+="]";
			c1 = c1.getSuperclass();
		}while(c1!=null);
		return r;
	}
	
	@Test
	public void testToString(){
		ObjectAnalyzer analyzer = new ObjectAnalyzer();
		int a[] = {1,2,3};
		System.out.println(analyzer.toString(a));
		System.out.println("==============");
		Bean1 b = new Bean1(1.0);
		System.out.println(analyzer.toString(b));
		System.out.println("==============");
		int c = 1;
		System.out.println(analyzer.toString(c));	//java.lang.Integer[value=1][][]，后面多出的两个[][]是其父类，Number和Object
	}
}
