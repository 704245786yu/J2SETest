package algorithm.merge;

import algorithm.AbstractAlgorithm;

/**归并排序
 * */
public class Merge extends AbstractAlgorithm {
	
	private static Comparable<?>[] aux;	//归并时需要的辅助数组
	
	/**原地归并
	 * 使用临时数组 aux[lo..hi] 来归并 a[lo..mid] 和 a[mid+1..hi]
	 * @param aux 辅助数组
	 * */
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
		//前提条件: a[lo .. mid] 和 a[mid+1 .. hi] 是已排序的子数组
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);
		
		// copy to aux[]
		for(int i=lo; i<=hi; i++)
			aux[i] = a[i];
		
		// merge back to a[]
		int i=lo, j=mid+1;	//i为aux[lo..mid]的游标，j为aux[mid+1..hi]的游标
		for(int k=lo; k<=hi; k++){
			if(i > mid)	//i>mid时，表示aux[lo..mid]已扫描完，剩下的只要将aux[mid+1..hi]剩余的都copy到a[]中即可
				a[k] = aux[j++];
            else if (j > hi)	//j>hi时，表示aux[mid+1..hi]已扫描完，剩下的只要将aux[lo..mid]剩余的都copy到a[]中即可
            	a[k] = aux[i++];
            else if (less(aux[j], aux[i]))	//右半边的当前元素小于左半边的当前元素，取右半边的元素
            	a[k] = aux[j++];
            else	//右半边的当前元素大于左半边的当前元素，取左半边的元素
            	a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] a) {
		aux = new Comparable[a.length];	//分配空间
		sort(a, 0, a.length-1);
	}
	
	/**将数组 a[lo..hi] 排序
	 * */
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi<=lo)
			return;
		int mid = lo+(hi-lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}

	public static void main(String[] args) {
		String[] a = {"S","H","E","L","L","S","O","R","T","E","X","A","M","P","L","E"};
		AbstractAlgorithm alg = new Merge();
		alg.sort(a);
		System.out.println(isSorted(a));
		show(a);
	}
}
