package DesignPattern.ChainOfResponsibility;

/**表示发生问题的类，即责任链要处理的事情。*/
public class Trouble {
    /**问题编号*/
    private int number;

    public Trouble(int number) {
        this.number = number;
    }

    /**获取问题编号*/
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Trouble{" + "number=" + number + '}';
    }
}
