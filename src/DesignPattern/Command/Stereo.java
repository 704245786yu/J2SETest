package DesignPattern.Command;

public class Stereo {
    public void on(){
        System.out.println("音响开启");
    }
    public void setCD(){
        System.out.println("放入CD");
    }
    public void setVolume(int volume){
        System.out.println("调节音量为:"+volume);
    }
    public void off(){
        System.out.println("关闭音响");
    }
}
