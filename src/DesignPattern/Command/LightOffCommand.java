package DesignPattern.Command;

/** 关闭电灯的命令 */
public class LightOffCommand implements Command{
    /**
     * 这是命令模式里的接收者,负责接收请求
     * */
    Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
