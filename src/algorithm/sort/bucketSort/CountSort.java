package algorithm.sort.bucketSort;

import java.util.Arrays;

import org.junit.Test;

/** 计数排序——桶排序的一种实现算法
 * 思考？计数排序是不稳定的？
 * @Date 2019-04-20
 */
public class CountSort {
    /**《算法基础》*/
    @Test
    public void testCountSort(){
        int arr[] = {2,1,1,0,3,7,5,1,3,4,2,1,0,2,7,4};
        int m = 8; //arr[]里有8种不同的值
        countSort(arr,arr.length,m);
        System.out.println("结果:arr"+Arrays.toString(arr));
    }

    /**@param n 待排序元素的个数
     * @param m 元素的不同取值个数
     * */
    public void countSort(int e[], int n, int m){
        int b[] = new int[n];   //存储排序后的序列
        int t[] = new int[m];   //计数数组，用于统计待排序元素各种值出现的次数
        for(int i=0; i<m; i++)  //初始化计数数组值都为0
            t[i] = 0;
        for(int i=0; i<n; i++)  //统计待排序元素中各元素值出现的次数。（待排序元素的值即为计数数组的下标）
            t[e[i]]++;
        System.out.println("t[]:"+Arrays.toString(t));

        /*各种值的开始存储下标。这里是从后往前计算的。*/
        for(int index = n, j=m-1; j>=0; j--)
            index = t[j] = index - t[j];
        System.out.println("t[]:"+Arrays.toString(t));
        /*存入对应位置，并修正下一个相同值的存储下标
        * 【提示】采用这种方式的好处是，可顺利的用该方法切换为对对象的排序。
        * */
        for(int i = 0; i<n; i++){
            b[t[e[i]]++] = e[i];
        }
        System.out.println("b[]:"+Arrays.toString(b));

        for(int i=0; i<n; i++)  //将排好序的 b 再整个赋值给 e。
            e[i] = b[i];
    }
}
