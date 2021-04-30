package DesignPattern.Flyweight;

import java.util.HashMap;
import java.util.Map;

public class Demo1 {

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.exec();
    }

    void exec(){
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly1 = factory.getFlyweight("Tom");
        Flyweight fly2 = factory.getFlyweight("Jerry");
    }

    /**该接口是抽象享元角色。它是产品的抽象类，同时定义出对象的外部状态和内部状态的接口或实现。*/
    interface Flyweight{
        void operation(ExtrinsicState state);
    }

    /**ConcreteFlyweight必须是可共享的，它保存的任何状态都必须是内部的，也就是说，ConcreteFlyweight必须和它的应用环境场合无关。*/
    class ConcreteFlyweight implements Flyweight{
        private IntrinsicState intrinsicState;

        @Override
        public void operation(ExtrinsicState state) {

        }
    }

    class UnsharedConcreteFlyweight implements Flyweight{

        @Override
        public void operation(ExtrinsicState state) {

        }
    }

    class FlyweightFactory{
        /**Flyweight池，存放内部状态，当客户端请求一个共享Flyweight时，这个Factory首先搜索池中是否已经有可适用的。
         * 如果有Factory只是简单的返回这个对象，否则创建一个新对象，加入到池中，再返回这个对象。*/
        private Map<Object, Flyweight> flyweights = new HashMap<>();

        public Flyweight getFlyweight(Object key){
            Flyweight flyweight = flyweights.get(key);
            if(flyweight == null){
                flyweight = new ConcreteFlyweight();
                flyweights.put(key, flyweight);
            }
            return flyweight;
        }
    }

    interface IntrinsicState{}
    interface ExtrinsicState{}
}
