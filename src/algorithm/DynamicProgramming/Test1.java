package algorithm.DynamicProgramming;

import org.junit.jupiter.api.Test;

public class Test1 {
    int[] p = {1,5,8,9,10,17,17,20,24,30, 1,5,8,9,10,17,17,20,24,30, 1,5,8,9,10,17,17,20,24,30, 1,5,8,9,10,17,17,20,24,30};//1~10英寸钢铁的价格

    //n表示钢条的长度
    public int curRod(int[] p, int n, boolean isPrint){
        if(n==0)
            return 0;
        int q = -1;
        for(int i=1; i<=n; i++) {
            int rni = curRod(p, n - i,false);
            int temp = p[i-1] + rni;
            q = Math.max(q, temp);
            if(isPrint)
                System.out.printf("n:%d p%d=%d r%d=%d --- %d\n",n,i,p[i-1],n-i,rni,temp);
        }
        return q;
    }

    @Test
    public void testCurRod(){
        System.out.println(curRod(p,30, false));
    }

    /**========带备忘的自顶向下的动态规划法==========*/
    public int memoizedCutRod(int[] p,int n){
        int[] r = new int[n+1];   //记录每个子问题的解，包括值为0的子问题
        for (int i=0; i<r.length; i++) {
            r[i] = -1;  //初始化每个子问题的值为-1
        }
        return memoizedCutRodAux(p,n,r);
    }

    private int memoizedCutRodAux(int[] p,int n, int[] r){
        int q = -1;
        if(r[n]>=0) //子问题若已有值则直接返回，不必继续计算
            return r[n];
        if(n==0)
            q = 0;
        else {
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, p[i-1] + memoizedCutRodAux(p, n - i, r));
            }
        }
        r[n] = q;   //将子问题的值记录下来
        return r[n];
    }

    @Test
    public void testMemoized(){
        long start = System.currentTimeMillis();
        int ri = memoizedCutRod(p,40);
        long end = System.currentTimeMillis();
        System.out.printf("花费时间：%d, ri=%d\n",end-start, ri);
    }

    /**==========自底向上版本的动态规划============*/
    public int bottomUpMemoizedCutRod(int[] p,int n){
        int[] r = new int[n+1];   //记录每个子问题的解，包括值为0的子问题
        r[0] = 0;   //长度为0的钢条没有收益
        //依次求解规模为i=1,2,...,n的子问题
        for (int i=1; i<=n; i++) {
            int q = -1;
            //先从最小子问题开始计算，自底向上。即从1开始计算。
            for(int j=1; j<=i; j++)
                q = Math.max(q, p[j-1] + r[i-j]);   //子问题的内部是比较从左往右切时的最优解
            r[i] = q;   //保存子问题的值，供父问题使用
       }
        return r[n];
    }

    @Test
    public void testBottomUpMemoized(){
        long start = System.currentTimeMillis();
        int ri = bottomUpMemoizedCutRod(p,40);
        long end = System.currentTimeMillis();
        System.out.printf("花费时间：%d, ri=%d\n",end-start, ri);
    }

    /**==========获取最优解本身============*/
    private int[] sectionLen = null;    //保存长度为i的钢管的最优解的第一段切割长度
    public int extendBottomUpMemoized(int[] p,int n){
        int[] r = new int[n+1];
        sectionLen = new int[n+1];
        r[0] = 0;
        for (int i=1; i<=n; i++) {
            int q = -1;
            for(int j=1; j<=i; j++) {
                int v = p[j - 1] + r[i - j];
                if(q<v){
                    q = v;
                    sectionLen[i] = j;//这是与bottomUpMemoizedCutRod()最大的区别
                }
            }
            r[i] = q;
        }
        return r[n];
    }

    @Test
    public void testExtendBottomUp(){
        int n = 8;  //切割长度为n的钢条
        int ri = extendBottomUpMemoized(p,n);
        System.out.printf("长度%d的钢条切割为：",n);
        while(n>0){
            System.out.print(sectionLen[n]+" ");
            n = n - sectionLen[n];
        }
        System.out.println("\n收益："+ri);
    }
}
