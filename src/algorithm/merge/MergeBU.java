package algorithm.merge;

import algorithm.AbstractAlgorithm;

/**自底向上的归并排序
 * */
public class MergeBU extends AbstractAlgorithm {
	
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
		//每轮循环归并的子数组大小，1,2,4,8,16,32...
		for(int sz=1; sz<a.length; sz=sz+sz)	//sz表示当前这一轮子数组大小
			//每次循环归并下一组子数组，我认为，a.length-sz也可写为-1，此处减sz只是把一个子数组看做整个数组的一个元素，而sz是这个元素的长度。
			for(int lo=0; lo<a.length-sz; lo+=sz+sz){	//lo+=sz+sz = lo+sz+sz，第一次加sz，lo跳到当前归并的第二个数组，第二次加sz，lo跳到下一次归并的第一个数组。
				int mid = lo+sz-1;
				int hi = Math.min(lo+sz+sz-1, a.length-1);	//最后一个子数组的大小只有在数组大小是sz的偶数倍的时候才会等于sz，否则它会比sz小，
																					//所以此句的作用是归并最后一组子数组时，获取第二子数组的末尾下标。
				merge(a, aux, lo, mid,  hi);
			}
	}
	
	public static void main(String[] args) {
		String[] a = {"S","H","E","L","L","S","O","R","T","E","X","A","M","P","L","E"};
		AbstractAlgorithm alg = new MergeBU();
		alg.sort(a);
		System.out.println(isSorted(a));
		show(a);
	}
}
