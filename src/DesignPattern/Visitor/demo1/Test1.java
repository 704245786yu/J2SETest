package DesignPattern.Visitor.demo1;

import org.junit.Test;

/**
 * @author zhiyu
 * @Date 2020-02-19
 */
public class Test1 {
    @Test
    public void test1(){
        EmployeeManage employeeManage = new EmployeeManage();
        employeeManage.add(new Employee("Tom", 4500, 8, 1));
        employeeManage.add(new Employee("Jerry", 6500, 10, 2));
        employeeManage.add(new Employee("Jack", 9600, 12, 3));

        employeeManage.accept(new CompensationVisitor());
    }
}
