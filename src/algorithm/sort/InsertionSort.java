package algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

/** 插入排序
 * @Date 2019-04-20
 */
public class InsertionSort {
    /**《算法基础》实现方法*/
    @Test
    public void testInsertionSort(){
        int arr[] = {8,7,6,5,4,3,2,1};
        insertionSort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public void insertionSort(int e[], int n){
        // i从第二元素开始做插入排序
        for(int i=1;i<n;i++){
            //保存当前要排序的元素的值，这样 e[i] 就可以空下来，让i之前大于e[i]的元素往后移动。
            int temp = e[i];
            int j;
            /* j 从当前要排序的元素的前一个元素开始，与当前元素的值比较。
            * 【技巧】：此处一个技巧，j 是从后往前遍历，这样的好处是遍历的时候碰到一个元素大于当前要排序元素时，
            * 就把这个元素往后移。而不是从首部开始遍历，在找到正确位置后，才开始把所有后面元素整体往后移。
            * */
            for(j=i-1;j>=0 && e[j]>temp;j--)
                e[j+1] = e[j];
            e[j+1] = temp;
        }
    }
}
