package GenericClasses;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

import org.junit.Test;

public class TestGetTypeParam {

	@Test
	public void test1(){
		ArrayList<String> a = new ArrayList<String>();
		TypeVariable[] tvAry = a.getClass().getTypeParameters();
		for(TypeVariable tv : tvAry)
			System.out.println(tv.getTypeName());
	}
	
}