package DesignPattern.Builder.demo1;

public class BMWModel extends CarModel{

	@Override
	protected void start() {
		System.out.println("BMW start");
	}

	@Override
	protected void alarm() {
		System.out.println("BMW alarm");
	}

	@Override
	protected void engineBoom() {
		System.out.println("BMW engineBoom");
	}

}
