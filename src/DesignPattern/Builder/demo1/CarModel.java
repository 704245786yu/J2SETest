package DesignPattern.Builder.demo1;

import java.util.ArrayList;

public abstract class CarModel {

	/**保存各个基本方法执行的顺序*/
	private ArrayList<String> sequence = new ArrayList<>();
	
	protected abstract void start();
	protected abstract void alarm();
	protected abstract void engineBoom();
	
	/**用于设置方法的执行顺序*/
	final public void setSequence(ArrayList<String> sequence){
		this.sequence = sequence;
	}
	
	/**保证了方法的执行顺序与sequence里所定义的顺序一致*/
	final public void run(){
		for(int i=0; i<sequence.size(); i++){
			String actionName = sequence.get(i);
			if(actionName.equalsIgnoreCase("start")){
				start();
			}else if(actionName.equalsIgnoreCase("alarm")){
				alarm();
			}else if(actionName.equalsIgnoreCase("engineBoom")){
				engineBoom();
			}
		}
	}
	
}
