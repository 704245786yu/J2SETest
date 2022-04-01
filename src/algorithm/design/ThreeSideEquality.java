package algorithm.design;

import org.junit.jupiter.api.Test;

/** 将A B C D E F 这6个变量排成三角形，这6个变量分别取[1,6]上的整数，且均不相同。
 * 求使三角形三条边上的变量之和相等的全部解。
 *      A                   1
 *   B     F             6     4
 * C    D     E       3     2      5
 * @Date 2019-04-19
 */
public class ThreeSideEquality {
    private int M = 6;
    private int a,b,c,d,e,f;
    private int p[] = new int[M+1]; //p[i] 表示整数 i 是否使用，1:未使用，0:已使用。这里用M+1只是为了操作方便无需进行数组下标转换
    private int pt[] = {a,b,c,d,e,f};

    /**回溯+递归方式实现*/
    @Test
    public void recall(){
        for(int i=0; i<=M; i++)
            p[i] = 1;
        searchAll(0);
    }

    /** 此递归函数作用：为第 k 个变量找一个还未使用过的整数。
     * 当整数 i 还未使用，并发现 k 位置放入 i 是合理时，将 i 放入第 k 变量。
     * 如果第 k 变量还不是最后一个变量，则递归，为后一个变量找数；如果是最后一个变量，则将找到的解输出。
     * @param k pt[] 中元素的下标，从0开始
     * */
    private void searchAll(int k){
        int i;
        for(i=1; i<=M; i++){
            /*k<4 时，表示当前才给前4个变量赋值，不构成2条边，所以直接赋值
            * k==4 时，表示当前要给第5个变量赋值，此时可对已有的2条边进行比较，比较结果相等则给 pt[k] 赋值
            * k==5 时，表示当前要给第6个变量赋值，此时可对已有的3条边进行两两比较，比较结果相等则给 p[k] 赋值
            * */
            if((p[i]==1) && (k<4 || (k==4 && pt[0]+pt[1]+pt[2] == pt[2]+pt[3]+i) || (k==5) && pt[0]+pt[1]+pt[2] == pt[2]+pt[3]+pt[4] && pt[0]+pt[1]+pt[2] == pt[4]+i+pt[0])){
                pt[k] = i;
                p[i] = 0;
//                System.out.println("pt:"+Arrays.toString(pt));
//                System.out.println("p:"+Arrays.toString(p));
                if(k==5){   // k 为最后一个变量，输出解
                    for(int j=0; j<M; j++)
                        System.out.printf("%4d", pt[j]);
                    System.out.println();
                }else
                    searchAll(k+1); //继续查找下一个k的值
                p[i]=1; //由于第 k+1 个变量的值已经遍历完了（无论它是否构成了匹配值），所以更改当前第 k 个变量的值，需要将原来已使用的值设为 1，表示未使用。
//                System.out.println("取消当前第"+(k+1)+"个变量的值"+pt[k]+",重新设置");
            }
        }
//        System.out.println("当设置第"+(k+1)+"个变量时退回");
    }
}
