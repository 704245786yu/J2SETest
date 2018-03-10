package reflection.javaBean;

public class Bean1 {

	private Integer[] a;
	private int[] b;
	private Double d;
	
	public Bean1() {}
	
	public Bean1(Double d) {
		this.d = d;
	}

	public Integer[] getA() {
		return a;
	}
	public void setA(Integer[] a) {
		this.a = a;
	}
	public int[] getB() {
		return b;
	}
	public void setB(int[] b) {
		this.b = b;
	}
	public Double getD() {
		return d;
	}
	public void setD(Double d) {
		this.d = d;
	}
	
}