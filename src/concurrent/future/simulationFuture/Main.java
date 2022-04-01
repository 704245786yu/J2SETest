package concurrent.future.simulationFuture;

/**模拟Future模式的实现，摘自《实战Java高并发程序设计》*/
public class Main {

	public static void main(String[] args) {
		Client client = new Client();
		//request()会立即返回，因为得到的是FutureDate而不是RealDate
		Data data = client.request("name");
		System.out.println("请求完毕");
		try {
            //使用sleep()模拟对其他业务逻辑的处理，在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		//使用真实的数据
        System.out.println("其他业务处理完毕，获取真实数据");
		System.out.println("数据："+data.getResult());
	}
	
}
