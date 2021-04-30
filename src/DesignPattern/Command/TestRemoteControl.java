package DesignPattern.Command;

public class TestRemoteControl {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        //创建请求接收者
        Light light = new Light();
        Stereo stereo = new Stereo();

        //创建命令
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);

        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        remoteControl.setCommand(1, stereoOnWithCDCommand, stereoOffCommand);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.undoButtonWasPushed();
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.undoButtonWasPushed();
    }
}
