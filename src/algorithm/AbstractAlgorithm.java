package algorithm;

/**排序算法的模板，该模板适用于任意实现了Comparable接口的数组类型*/
public abstract class AbstractAlgorithm {

	/**对数组进行升序排序*/
	@SuppressWarnings("rawtypes")
	public abstract void sort(Comparable[] a);
	
	/**比较v是否小于w
	 * @return v<w返回true
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static <T> boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	/**交换元素位置*/
	@SuppressWarnings("rawtypes")
	protected static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	/**打印数组内容*/
	@SuppressWarnings("rawtypes")
	public static void show(Comparable[] a){
		for(Comparable temp : a)
			System.out.print(temp+" ");
		System.out.println();
	}
	
	/***************************************************************************
	  *  Check if array is sorted - useful for debugging.
	  ***************************************************************************/
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] a){
		return isSorted(a, 0, a.length-1);
	}
	
	//此方法会在如归并排序中判断子数组是否有序时有用
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] a, int lo, int hi){
		for(int i=lo+1; i<=hi; i++)
			if( less(a[i], a[i-1]) )//后一个小于前一个就返回false
				return false;
		return true;
	}
	
	/*public static void main(String[] args){
		String[] a = {};	//In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}*/
}
