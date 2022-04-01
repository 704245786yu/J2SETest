package algorithm.stringMatching.KMP;

import java.util.Arrays;

import org.junit.Test;

/**
 * @Date 2019-02-26
 */
public class KMP {
    /**测试求next数组的方法*/
    @Test
    public void testNextAry() {
//        String pattern = "xyxxyzxz";
        String pattern = "abababca";
        char[] patternAry = pattern.toCharArray();
        int[] next = new int[patternAry.length];
//        faillink(charAry, next);
        getNextAray(patternAry, next);
//        myselfGetNextAray(patternAry, next);
        System.out.println(Arrays.toString(next));
    }

    /*===================实现方式1：摘自自考《算法基础》====================*/
    /**计算出模式字符串的各字符失败链接值*/
    void faillink(char[] pattern, int[] next){
        int k;
        next[0] = -1;
        for(int j=1; j < pattern.length; j++){
            k = next[j-1];
            while(k != -1 && pattern[k+1]!=pattern[j])
                k = next[k];
            if(pattern[j] == pattern[k+1])
                next[j] = k+1;
            else
                next[j] = -1;
        }
    }

    int kmpMatch(char[] str, char[] pattern){
        int[] next = new int[pattern.length];
        faillink(pattern, next);
        int i=0,j=0;
        while(i<str.length){
            while(j != -1 && str[i] != pattern[j])
                j = next[j];
            i++;
            j++;
            if(j==pattern.length-1)
                return i;
        }
        return -1;
    }
    /*===================实现方式：摘自网络==================*/
    @Test
    public void testKMP(){
//        String targetStr = "xysdfxyxxyzxyxxyzxz";
        String targetStr = "ababababca";
        char[] targetAry = targetStr.toCharArray();
        String pattern = "abababca";
//        String pattern = "xyxxyzxz";
        char[] patterAry = pattern.toCharArray();
        System.out.printf("在下标[%d]匹配", getKMP(targetAry, patterAry));
    }

    int getKMP(char[] str, char[] pattern){
        int[] next = new int[pattern.length];
        myselfGetNextAray(pattern, next);

        int j = 0;  // j 为模式串的下标
        int i = 0;    //  i 为目标串str的下标
        while(i<str.length && j<pattern.length){
            if(str[i] == pattern[j]){
                i++;
                j++;
            }else{
                if(j==0)
                    i++;
                else
                    j = next[j-1];
            }
        }
        if(j == pattern.length)
            return i-j;
        return -1;  //匹配失败
    }

    /**计算出next数组值*/
    void getNextAray(char[] pattern, int[] next){
        next[0] = -1;
        int i = 0, j = -1;
        while(i<pattern.length){
            if(j == -1 || pattern[i] == pattern[j]){
                j++;
                next[i] = j;
                i++;
            }else{
                if(j==0) {
                    i++;
                    j = next[0];
                }else
                    j = next[j-1];
            }
        }
    }

    /**自己实现的求next数组的方法*/
    void myselfGetNextAray(char[] pattern, int[] next){
        next[0] = -1;
        int i = 1, j = 0;// i 为后缀串的下标。j 即作为前缀串的下标，同时做PMT的值
        while(i<pattern.length){
            // j 为 -1 时，表示在pattern[0]处失配
            System.out.println(j);
            if(j==-1 || pattern[i] == pattern[j]){
                ++j;
                next[i] = j;
                ++i;
            }else{
                j = next[j-1];  //当比较失配时，将 j 回溯到 next[j-1] 的位置
            }
        }
    }
}
