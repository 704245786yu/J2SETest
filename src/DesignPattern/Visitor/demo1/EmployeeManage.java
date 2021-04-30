package DesignPattern.Visitor.demo1;

import java.util.HashMap;
import java.util.Map;

/**用于管理Employee
 * @author zhiyu
 * @Date 2020-02-19
 */
public class EmployeeManage {
    private Map<String, Employee> employees = new HashMap<>();

    public void add(Employee employee){
        employees.put(employee.getName(), employee);
    }

    //Visitor注册给业务对象，业务对象又会将自己暴露给Visitor
    public void accept(Visitor visitor){
        for(Employee e : employees.values()){
            e.accept(visitor);
        }
    }
}
