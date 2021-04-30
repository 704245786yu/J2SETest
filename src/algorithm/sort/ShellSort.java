package algorithm.sort;

import algorithm.AbstractAlgorithm;

/**希尔排序，又称缩小增量排序(diminishing increment sort)
 * */
public class Shell extends AbstractAlgorithm {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		System.out.println("N："+N);
		int h = 1;
		System.out.print("h：");
		while(h<N/3){
			System.out.print(h+",");
			h = 3*h + 1;	//每次h计算后得到的值：1,4,13,40,121,364,1093...
		}
		System.out.println(h);
		
		while(h>=1){
			//将数组变为h有序
			System.out.println("\nwhile h："+h);
			for(int i=h; i< N; i++){
				System.out.println("    i："+i);
				//对元素间隔为h的数组进行插入排序。将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]...之中
				for(int j=i; j>=h; j-=h){
					System.out.print("        a["+j+"]="+a[j]+", a["+(j-h)+"]="+a[j-h]);
					if( less(a[j], a[j-h]) ){
						exch(a, j, j-h);
						System.out.print(" -> exch：");
						show(a);
					}else{
						System.out.println();
					}
				}
			}
			h = h/3;
		}
	}

	public static void main(String[] args) {
		String[] a = {"S","H","E","L","L","S","O","R","T","E","X","A","M","P","L","E"};
		AbstractAlgorithm alg = new Shell();
		alg.sort(a);
		System.out.println(isSorted(a));
		show(a);
	}

	public void shellSort(int e[],int n){
	    for(int g=n/2; g>=1; g=g/3+1){
            /* i是从第1个子序列开始做插入排序.因为每个子序列的步长为g，所以第1个子序列的第1个元素下标为0，第2个元素下标为g。
            * 第2个子序列第一个元素下标为1，第2个元素下标为1+g，之后的子序列以此类推。
            * */
	        for(int i=g;i<n;i++){
	            int t,j;
                for(t=e[i],j=i; j>g && t<e[j-g]; j-=g)
                    e[j] = e[j-g];
                e[j] = t;
            }
        }
    }
}
