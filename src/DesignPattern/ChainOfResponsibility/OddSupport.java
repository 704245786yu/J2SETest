package DesignPattern.ChainOfResponsibility;

/**解决编号是奇数的Trouble*/
public class OddSupport extends Support {
    public OddSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if(trouble.getNumber() % 2 == 1)
            //To Do...这里执行解决问题的具体步骤
            return true;
        else
            return false;
    }
}
