package socket;

import org.junit.jupiter.api.Test;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**NetworkInterface提供访问网卡设备的相关信息
 * */
public class TestNetworkInterface {

    @Test
    public void test1() throws SocketException {
        //返回此机器上的所有接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println("name:"+networkInterface.getName()); //网络设备的名称
            System.out.println("displayName:"+networkInterface.getDisplayName());   //设备的名称（包含厂商、网卡型号等信息）
            System.out.println("index:"+networkInterface.getIndex());   //网络接口的索引
            System.out.println("isUp:"+networkInterface.isUp());    //网络接口是否已经开启并正常工作
            System.out.println("isLoopback:"+networkInterface.isLoopback());    //是否为回环接口
            System.out.println("mtu:"+networkInterface.getMTU());
            System.out.println();
        }
    }
}
