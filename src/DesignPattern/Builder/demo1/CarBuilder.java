package DesignPattern.Builder.demo1;

import java.util.ArrayList;

/**该抽象类用于创建各种车辆模型
 * 通过给定一个汽车的运行顺序，然后返回一辆车
 * */
public abstract class CarBuilder {

	/**设置CarModel内基本方法的执行顺序*/
	public abstract void setSequence(ArrayList<String> sequence);
	/**设置完毕顺序后，获取车辆模型*/
	public abstract CarModel getCarModel();
}
