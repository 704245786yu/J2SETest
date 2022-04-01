package J2SEClass.object_oriented.inherit;

/**
 * @author zhiyu
 * @Date 2020-03-19
 */
public class Parent {
    public void F1(){
        F2();
    }

    private void F2(){
        System.out.println("Parent F2");
    }
}
