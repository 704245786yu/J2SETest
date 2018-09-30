package DesignPattern.ChainOfResponsibility;

/**解决编号小于limit值的Trouble*/
public class LimitSupport extends Support{
    private int limit;

    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if(trouble.getNumber() < limit)
            //To Do...这里执行解决问题的具体步骤
            return true;
        else
            return false;
    }
}
