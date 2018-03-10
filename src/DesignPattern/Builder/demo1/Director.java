package DesignPattern.Builder.demo1;

import java.util.ArrayList;

public class Director {

	private ArrayList<String> sequence = new ArrayList<>();
	private BenzBuilder benzBuilder = new BenzBuilder();
	private BMWBuilder bmwBuilder = new BMWBuilder();
	
	public BenzModel getABenzModel(){
		//注意，这里需清理场景
		sequence.clear();
		sequence.add("start");
		sequence.add("alarm");
		benzBuilder.setSequence(sequence);
		return (BenzModel)benzBuilder.getCarModel();
	}
	
	public BenzModel getBBenzModel(){
		//注意，这里需清理场景
		sequence.clear();
		sequence.add("engineBoom");
		sequence.add("alarm");
		benzBuilder.setSequence(sequence);
		return (BenzModel)benzBuilder.getCarModel();
	}
}
