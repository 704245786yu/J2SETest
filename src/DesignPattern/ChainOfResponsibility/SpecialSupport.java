package DesignPattern.ChainOfResponsibility;

/**只能解决指定编号的Trouble*/
public class SpecialSupport extends Support{
    private int number;

    public SpecialSupport(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if(trouble.getNumber() == number)
            //To Do...这里执行解决问题的具体步骤
            return true;
        else
            return false;
    }
}
