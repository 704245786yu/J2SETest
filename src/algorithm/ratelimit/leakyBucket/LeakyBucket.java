package algorithm.ratelimit.leakyBucket;

/**此漏桶算法根本无用！！
 * */
@Deprecated
public class LeakyBucket {
    long refreshTime; //time for last water refresh
    long water; //water count at refresh time 当前水量（剩余水量）
    long rate; //leak rate in calls/s 水漏出的速度
    long capacity; // burst; 桶的容量

    public void refreshWater(){
        long now = System.currentTimeMillis();
        //先执行漏水，计算剩余水量。水随着时间流逝，不断流走，最多就流干到0。
        water = Math.max(0, water - (now - refreshTime) * rate); //时间间隔 * rate = 漏出的水量。漏桶的漏水就是通过时间差来计算漏水量。
        refreshTime = now;
    }

    public boolean grant(){
        refreshWater();
        if((water + 1) < capacity){ //尝试加水，并且水还未满
            water++;
            return true;
        }else{  //水满，拒绝加水
            return false;
        }
    }
}
