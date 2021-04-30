package DesignPattern.Command;

/**宏命令。用一个宏命令来执行多个命令*/
public class MacroCommand implements Command{
    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands){
            command.execute();
        }
    }

    @Override
    public void undo() {

    }
}
