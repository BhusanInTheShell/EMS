// Regular Employee class
public class RegularEmployee extends Employee {
    private double overtimePay;

    public RegularEmployee(String id, String name, String department, double baseSalary) {
        super(id, name, department, baseSalary);
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + overtimePay;
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: Regular, Salary: " + calculateSalary();
    }
}