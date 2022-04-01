package J2SEClass.log;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * @Date 2019-01-05
 */
public class Demo1 {

    /**日志基本操作*/
    @Test
    public void test1(){
        //获取全局日志记录器
        Logger logger = Logger.getGlobal();
        /* 输出：
        * 一月 05, 2019 9:32:44 下午 J2SEClass.log.Demo1 test1
        * 信息: msg
        * */
        logger.info("msg");
    }

    /**取消所有的日志记录*/
    @Test
    public void test2(){
        Logger logger = Logger.getGlobal();
        //取消所有的日志
        logger.setLevel(Level.OFF);
        logger.info("msg");
    }

    /**使用自定义的日志记录器*/
    @Test
    public void test3(){
        /* 输出：
        * 一月 05, 2019 9:45:27 下午 J2SEClass.log.Demo1 test3
        * 信息: msg
        * */
        Logger logger = Logger.getLogger("myLogger");
        logger.info("msg");
    }

    /**测试打印info级别以下的日志
     * 无任何输出，这是因为，如果要记录info级别以下的日志，除了需要修改日志记录级别，还要修改日志处理器的配置。
     * */
    @Test
    public void test4(){
        Logger logger = Logger.getGlobal();
        logger.config("config");
        logger.setLevel(Level.CONFIG);
        logger.config("config");

    }
}
