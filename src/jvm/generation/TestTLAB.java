package jvm.generation;

public class TestTLAB {

	public static void alloc(){
		byte[] b = new byte[2];
		b[0]=1;
	}
	
	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		for(int i=0; i<10_000_000; i++){
			alloc();
		}
		System.out.println(System.currentTimeMillis() - a);
	}
}
