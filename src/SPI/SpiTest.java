package SPI;

import java.util.ServiceLoader;

import org.junit.Test;

/**
 * @Date 2019-01-08
 */
public class SpiTest {
    @Test
    public void sayHello(){
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
