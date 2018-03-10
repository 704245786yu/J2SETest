package DesignPattern.Builder.demo1;

import java.util.ArrayList;

public class BMWBuilder extends CarBuilder {

	private BMWModel bMW = new BMWModel();
	
	@Override
	public void setSequence(ArrayList<String> sequence) {
		bMW.setSequence(sequence);
	}

	@Override
	public CarModel getCarModel() {
		return bMW;
	}

}
