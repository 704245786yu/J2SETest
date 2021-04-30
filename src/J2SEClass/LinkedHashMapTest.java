import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;

public class LinkedHashMapTest {

	@Test
	public void test(){
		LinkedHashMap<Integer,String> map = new LinkedHashMap<Integer,String>();
		map.put(3, "3333");
		map.put(10, "10");
		map.put(2, "22222");
		map.put(7, "77777");
		for(Integer key : map.keySet()){
			System.out.println(key);
		}
		map.get("3");
		for(Integer key : map.keySet()){
			System.out.println(key);
		}
	}
	
	@Test
	public void test1(){
		HashMap<String,String> map = new HashMap<>();
		map.put("a", "a");
	}
}
