package pinyin;

import org.junit.Test;

public class TestPinyin {

	@Test
	public void test1(){
		PinYin4j t=new PinYin4j();
		String str = "农业银行1234567890abcdefghijklmnopqrstuvwxyz*";
		System.out.println(t.makeStringByStringSet(t.getPinyin(str)));
	}
	
	@Test
	public void test2(){
		PinYin4j t=new PinYin4j();
		System.out.println(t.getPinyinStr("农业银行"));
	}
}
