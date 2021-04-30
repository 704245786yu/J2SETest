package DesignPattern.Command;

/**
 * TestSimpleRemoteControl类是命令模式的客户。
 * 客户负责创建一个具体的Command，并设置其接收者。
 * */
public class TestSimpleRemoteControl {
    public static void main(String[] args) {
        //遥控器就是【调用者】，传入一个命令对象，可以用来发出请求
        SimpleRemoteControl remoteControl = new SimpleRemoteControl();

        Light light = new Light();  //电灯对象是请求的接收者
        LightOnCommand lightOnCommand = new LightOnCommand(light);  //创建一个命令，将请求接收者传给它

        remoteControl.setCommand(lightOnCommand);   //把命令传递给调用者
        remoteControl.buttonWasPressed();
    }
}
