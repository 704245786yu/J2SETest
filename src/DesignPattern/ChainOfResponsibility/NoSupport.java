package DesignPattern.ChainOfResponsibility;

/**这是一个永远“不解决问题”的类d。*/
public class NoSupport extends Support {

    public NoSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }
}
