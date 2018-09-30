package DesignPattern.ChainOfResponsibility;

/**用来解决问题的抽象类，它是职责链上的对象*/
public abstract class Support {
    private String name;

    /**职责链中下一个要解决问题的对象
     * 由此也可以看书，职责链其实就用到了链式结构。
     * */
    private Support next;

    public Support(String name) {
        this.name = name;
    }

    public Support setNext(Support next) {
        this.next = next;
        return next;
    }

    /**每个具体的Support类都需要实现的方法，这里是实际解决问题的地方。*/
    protected abstract boolean resolve(Trouble trouble);

    /**解决问题的步骤，包含本对象实际解决问题的resolve()方法，
     * 以及根据情况选择是否将问题传给下一个Support对象处理。*/
    /*
    public final void support(Trouble trouble){
        if(resolve(trouble)){
            done(trouble);
        }else if(next != null){
            next.support(trouble);
        }else{
            fail(trouble);  //当next==null时，则到了链尾端之外，表示处理失败，链上没有Support可以处理该问题。
        }
    }
    */
    /**！！！不使用递归，而使用循环来实现的support！！！*/
    public final void support(Trouble trouble){
        for(Support support = this; true; support = support.next){
            if(support.resolve(trouble)){
                support.done(trouble);
                break;
            }else if(support.next == null){
                support.fail(trouble);
                break;
            }
        }
    }

    /**问题解决后的方法*/
    protected void done(Trouble trouble){
        System.out.println(trouble+" is resolved by "+this+".");
    }
    /**问题未解决后的方法*/
    protected void fail(Trouble trouble){
        System.out.println(trouble+" cannot be resolved.");
    }

    @Override
    public String toString() {
        return "Support "+name;
    }
}
