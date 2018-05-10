package algorithm.quick;

import algorithm.AbstractAlgorithm;

/**自底向上的归并排序
 * */
@SuppressWarnings("rawtypes")
public class Quick extends AbstractAlgorithm {
	
	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi<=lo)
			return;
		int j = partition(a, lo, hi);	// j 指向了当前的切分元素
		sort(a, lo, j-1);	//将切分的左半部分a[lo..j-1]排序
		sort(a, j+1, hi);	//将切分的右半部分a[j+1..hi]排序
	}
	
	/**将数组切分为a[lo..i-1], a[i], a[i+1..hi]，每次切分完毕，a[i]左侧的元素都不大于a[i]，右侧的元素都不小于a[j]。
	 * */
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j=hi+1;	//左右扫描指针
		Comparable v = a[lo];	//v用于保存切分元素a[lo]
		while(true){
			while( less(a[ ++i ], v) )	//从左扫描，碰到大于等于v的a[++i]则停止循环
				if( i==hi )	//如果扫描到数组的最后一个元素，则停止扫描，防止访问数组越界。此时切分元素v为数组里最大的元素。
					break;
			while( less(v,a[ --j ]) )	//从右扫描，碰到小于等于v的a[--j]则停止循环
				if( j==lo )	//如果扫描到数组的第一个元素，则停止扫描，防止访问数组越界。此时切分元素v为数组里最小的元素。
					break;	//j==lo这个测试条件是冗余的，因为切分元素就是a[lo]，它不可能比自己小。
			if( i>=j )	//切分元素的左右两侧已扫描完毕，
				break;
			exch(a, i, j);	//保证切分元素的左侧都不大于v，右侧都不小于v
		}
		exch(a, lo, j);	//此时 j 所指的元素一定是不大于v的，所以交换v和a[j]的值，保证了切分元素左边的元素都小于等于a[j]
		return j;
	}
	
	public static void main(String[] args) {
		String[] a = {"S","H","E","L","L","S","O","R","T","E","X","A","M","P","L","E"};
		AbstractAlgorithm alg = new Quick();
		alg.sort(a);
		System.out.println(isSorted(a));
		show(a);
	}
}
