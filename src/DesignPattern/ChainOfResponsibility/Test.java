package DesignPattern.ChainOfResponsibility;

/**职责链模式*/
public class Test {
    public static void main(String[] args) {
        Support support1 = new NoSupport("NoSuppport_1");
        Support support2 = new LimitSupport("LimitSupport_2",5);
        Support support3 = new SpecialSupport("SpecialSupport_3",7);
        Support support4 = new LimitSupport("LimitSupport_4",10);
        Support support5 = new OddSupport("OddSupport_5");
        Support support6 = new LimitSupport("LimitSupport_6",12);
        //形成职责链
        support1.setNext(support2).setNext(support3).setNext(support4).setNext(support5).setNext(support6);
        //制造Trouble，并交由职责链处理
        for (int i = 0; i < 20; i++) {
            support1.support(new Trouble(i));
        }
    }
}
