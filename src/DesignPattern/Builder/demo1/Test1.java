package DesignPattern.Builder.demo1;

import java.util.ArrayList;

import org.junit.Test;

public class Test1 {

	@Test
	public void test1(){
		BenzModel benz = new BenzModel();
		//设置CarModel方法的执行顺序
		ArrayList<String> sequence = new ArrayList<>();
		sequence.add("alarm");
		sequence.add("start");
		sequence.add("engineBoom");
		benz.setSequence(sequence);
		benz.run();
		
		BMWModel bMW = new BMWModel();
		//设置CarModel方法的执行顺序
		ArrayList<String> sequence1 = new ArrayList<>();
		sequence1.add("start");
		sequence1.add("engineBoom");
		sequence1.add("alarm");
		bMW.setSequence(sequence1);
		bMW.run();
	}
	
	@Test
	public void test2(){
		ArrayList<String> sequence = new ArrayList<>();
		sequence.add("alarm");
		sequence.add("start");
		sequence.add("engineBoom");
		
		//通过CarBuilder获取CarModel
		BenzBuilder benzBuilder = new BenzBuilder();
		benzBuilder.setSequence(sequence);
		BenzModel benz = (BenzModel)benzBuilder.getCarModel();
		benz.run();
		
		BMWBuilder bmwBuilder = new BMWBuilder();
		bmwBuilder.setSequence(sequence);
		BMWModel bmw = (BMWModel)bmwBuilder.getCarModel();
		bmw.run();
	}
	
	@Test
	public void test3(){
		Director director = new Director();
		director.getABenzModel().run();
		director.getBBenzModel().run();
	}
}
