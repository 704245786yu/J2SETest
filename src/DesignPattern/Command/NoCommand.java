package DesignPattern.Command;

/**该类的作用在于，在遥控器中不用每次都检查是否某个插槽都加载了命令，即避免了非空的判断！
 * NoCommand是一个空对象（null object）的例子。当你不想返回一个有意义的对象时，空对象就很有用。*/
public class NoCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
