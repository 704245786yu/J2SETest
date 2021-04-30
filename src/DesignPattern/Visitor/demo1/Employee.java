package DesignPattern.Visitor.demo1;

/**
 * @author zhiyu
 * @Date 2020-02-19
 */
public class Employee extends Element {

    private String name;
    private float income;//收入
    private int vacationDays;//年假
    private int degree; //职级

    public Employee() {
    }

    public Employee(String name, float income, int vacationDays, int degree) {
        this.name = name;
        this.income = income;
        this.vacationDays = vacationDays;
        this.degree = degree;
    }

    /**这里就是双重指派。visitor指派给employee，employee又会把自己指派给visitor*/
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
