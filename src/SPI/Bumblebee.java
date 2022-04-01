package SPI;

/**
 * @Date 2019-01-08
 */
public class Bumblebee implements Robot{

    public Bumblebee(){
        System.out.println("invoke Bumblebee()");
    }

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
