package DesignPattern.Command;

/** 打开电灯的命令 */
public class LightOnCommand implements Command{
    /**
     * 这是命令模式里的接收者,负责接收请求
     * */
    Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
