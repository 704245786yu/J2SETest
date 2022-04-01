package J2SEClass.innerclass;

import java.util.Observable;
import java.util.Observer;

/**匿名内部类
 * @author zhiyu
 * @Date 2020-09-30
 */
public class Anonymity {
    public static void main(String[] args) {
        //该内部类的名字为Anonymity$1
        Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {

            }
        };
        //该内部类的名字为Anonymity$2
        Observer observer2 = new Observer() {
            @Override
            public void update(Observable o, Object arg) {

            }
        };
        observer.update(new Observable(),1);
    }
}
