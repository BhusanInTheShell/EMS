import java.util.Date;
import java.text.SimpleDateFormat;

// Intern class
public class Intern extends Employee {
    private Date endDate;

    public Intern(String id, String name, String department, double baseSalary, Date endDate) {
        super(id, name, department, baseSalary);
        this.endDate = endDate;
    }

    @Override
    public double calculateSalary() {
        return baseSalary; // Interns don't get bonuses or overtime
    }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return super.toString() + ", Type: Intern, Salary: " + calculateSalary() + 
               ", Internship End Date: " + sdf.format(endDate);
    }
}