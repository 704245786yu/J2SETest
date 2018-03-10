package jvm.gc;

/**一次对象自我拯救的演示
 * 代码演示了两点：
 * 1、对象可在被GC时自我拯救
 * 2、这种自救的机会只有一次，因为对象的finalize()方法最多只会被系统自动调用一次
 * */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive(){
		System.out.println("yes, I am still alive :)");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed!");
		SAVE_HOOK = this;	//在被GC时自我拯救
	}
	
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		//因为finalize()方法优先级很低，所以暂停0.5秒等它
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("no, I am dead :(");
		
		//下面这段代码与上面的完全相同，但这次自救却失败了
		SAVE_HOOK = null;
		System.gc();
		//因为finalize()方法优先级很低，所以暂停0.5秒等它
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("no, I am dead :(");
	}
	
}
