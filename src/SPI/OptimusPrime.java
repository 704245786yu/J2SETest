package SPI;

/**
 * @Date 2019-01-08
 */
public class OptimusPrime implements Robot{
    public OptimusPrime(){
        System.out.println("invoke OptimusPrime()");
    }

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }
}
