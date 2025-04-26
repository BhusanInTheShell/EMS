// Manager class demonstrating inheritance
public class Manager extends Employee {
    private double bonus;

    public Manager(String id, String name, String department, double baseSalary) {
        super(id, name, department, baseSalary);
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: Manager, Salary: " + calculateSalary();
    }
}