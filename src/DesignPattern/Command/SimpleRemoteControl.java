package DesignPattern.Command;

/**遥控器，是命令模式里的调用者*/
public class SimpleRemoteControl {
    /**插槽slot持有命令，而这个命令控制着一个装置*/
    Command slot;

    /**该方法用于设置插槽控制的命令。*/
    public void setCommand(Command command) {
        this.slot = command;
    }

    /**当按下按钮时，就会调用对应命令的execute()方法。*/
    public void buttonWasPressed(){
        slot.execute();
    }
}
