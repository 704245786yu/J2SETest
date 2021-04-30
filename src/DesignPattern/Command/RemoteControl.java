package DesignPattern.Command;

import java.util.Arrays;

public class RemoteControl {
    Command[] onCommands;   //开命令
    Command[] offCommands;  //关命令
    Command undoCommand;    //记录前一个命令，用于撤销操作

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    /**打开开关按下*/
    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    /**关闭开关按下*/
    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    /**遥控器的撤销按钮*/
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }

    @Override
    public String toString() {
        return "RemoteControl{" +
                "onCommands=" + Arrays.toString(onCommands) +
                ", offCommands=" + Arrays.toString(offCommands) +
                '}';
    }
}
