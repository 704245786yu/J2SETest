package DesignPattern.Visitor.demo1;

/**年假补偿金
 * @author zhiyu
 * @Date 2020-02-19
 */
public class CompensationVisitor implements Visitor{
    @Override
    public void visit(Element element) {
        Employee employee = (Employee)element;
        System.out.println(employee.getName()+"'s compensation is "+(employee.getDegree() * employee.getVacationDays() *100));
    }
}
