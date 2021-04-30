package DesignPattern.Command;

/** 命令接口 */
public interface Command {
    /**
     * execute方法调用请求接收者对象
     * */
    void execute();

    /** 撤销命令 */
    void undo();
}
