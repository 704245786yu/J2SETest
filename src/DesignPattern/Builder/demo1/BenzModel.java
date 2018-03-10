package DesignPattern.Builder.demo1;

public class BenzModel extends CarModel{

	@Override
	protected void start() {
		System.out.println("Benz start");
	}

	@Override
	protected void alarm() {
		System.out.println("Benz alarm");
	}

	@Override
	protected void engineBoom() {
		System.out.println("Benz engineBoom");
	}

}
