package J2SEClass;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringTest {

	@Test
	public void replace(){
		String str = "aaaCtrl";
		System.out.println(str.replace("Ctrl", ""));
	}
	
	@Test
	public void testNull(){
		String a = null;
		String b = "b";
		System.out.println(b+a);//bnull
	}
	
	@Test
	public void test1(){
		String a = "aaa";
		invokedByTest1(a);
		System.out.println(a);
	}
	
	private void invokedByTest1(String b){
		b="bbb";
	}
	
	/**测试了null值可以直接转换为包装类，不会抛异常*/
	@Test
	public void test2(){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("s", null);
		String s = (String)map.get("s");
		System.out.println(s);
	}
	
	@Test
	public void test3(){
		String camel = "GenericClassBiz";
		camel = camel.substring(0, camel.length()-3);
		System.out.println(camel);//GenericClassGen
		int A = 'A';
		System.out.println(A);	//65
		char[] chars = camel.toCharArray();
		StringBuilder sb = new StringBuilder();
		sb.append(chars[0]);
		for(int i=1;i<chars.length;i++){
			char s = chars[i];
			if(s+1>65 && s+1<91){
				sb.append('_');
				sb.append(s);
				continue;
			}
			sb.append(s);
		}
		System.out.println(sb.toString().toUpperCase());
	}
	
	//JVM中具有String常量池缓存的功能
	@Test
	public void test4(){
		String a = "a";
		String b = "a";
		System.out.println(a==b);//true
	}
	
	@Test
	public void test5(){
		String str1 = "str";
		String str2 = "ing";

		String str3 = "str" + "ing";
		String str4 = str1 + str2;
		System.out.println(str3 == str4);//false

		String str5 = "string";
		System.out.println(str3 == str5);//true
	}

	@Test
	public void test6(){
		Set<String> set = new HashSet<>();
		set.add("aa");
		set.add("bb");
		List<String> list = new ArrayList<>(set);
		set.addAll(list);
		System.out.println(list);
	}

	@Test
	public void test7(){
		String a = null;
		String b = null;
		System.out.println(a+"/"+b);
	}

	// 过滤特殊字符
	@Test
    public void replaceAll(){
        String str = "是豆腐，()哈【】哈+hhh";

        String regEx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?￥%…（）_+|【】‘；：”“’。，、？\\s]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(str);
//        System.out.println(m.replaceAll(""));

	    String specificSymbol = "[\\(\\)\\+]";
        System.out.println(specificSymbol);
        System.out.println(str.replaceAll(regEx,""));
    }

    @Test
    public void join(){
	    String[] a = new String[2];
	    a[1] = "a";
        System.out.println(String.join(",",a));
    }

    @Test
    public void findSlash(){
	    String a = "lla/啊啊啊";
        System.out.println(a.contains("/"));
        String[] strs = a.split("/");
        for(String s : strs)
            System.out.println(s);
    }

    @Test
    public void format(){
	    Long id = 1000L;
        String str = String.format("AND ck.medicalAreaId = %1$d AND g47.medicalAreaId = %1$d",id);
        System.out.println(str);

        str = String.format("没有对应的target upstreamName:%s, target:%s, machineRoom:%s", "ups", "172", "test");
        System.out.println(str);
    }
    @Test
    public void tt(){
        String str="PluginRules";
        str=str.substring(0,1).toLowerCase().concat(str.substring(1));
        System.out.println(str);
    }
}
