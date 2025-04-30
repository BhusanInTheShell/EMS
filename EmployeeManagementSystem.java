import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeManagementSystem {
    private List<Employee> employees;
    private String dataFile;

    public EmployeeManagementSystem(String dataFile) {
        this.dataFile = dataFile;
        this.employees = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public void loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Employee data loaded successfully. Total employees: " + employees.size());
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with empty employee list.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employee data: " + e.getMessage());
        }
    }

    public void saveEmployees() {
        try {
            File backup = new File(dataFile + ".backup");
            File original = new File(dataFile);
            if (original.exists()) {
                Files.copy(original.toPath(), backup.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
                oos.writeObject(employees);
                System.out.println("Employee data saved successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    public void addEmployee(Employee employee) {
        if (findEmployeeById(employee.getId()) != null) {
            System.out.println("Employee with ID " + employee.getId() + " already exists.");
            return;
        }
        employees.add(employee);
        System.out.println("Employee added successfully: " + employee.getName());
    }

    public void removeEmployee(String id) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getId().equals(id)) {
                iterator.remove();
                System.out.println("Employee removed successfully: " + emp.getName());
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public Employee findEmployeeById(String id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                return emp;
            }
        }
        return null;
    }

    public List<Employee> findEmployeesByName(String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                result.add(emp);
            }
        }
        return result;
    }

    public List<Employee> findEmployeesByPerformance(int rating) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPerformanceRating() == rating) {
                result.add(emp);
            }
        }
        return result;
    }

    public void listAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
            return;
        }
        System.out.println("\nList of all employees:");
        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }
    }

    public void updateEmployee(String id, String name, String department, double baseSalary) {
        Employee emp = findEmployeeById(id);
        if (emp != null) {
            emp.setName(name);
            emp.setDepartment(department);
            emp.setBaseSalary(baseSalary);
            System.out.println("Employee updated successfully: " + emp.getName());
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void generateSampleData() {
        employees.clear();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            addEmployee(new RegularEmployee("E001", "Ramu Kaka", "IT", 50000, 5000));
            addEmployee(new Manager("E002", "Kabir Singh", "HR", 70000, 10000));
            addEmployee(new Intern("E003", "Hari Bahadur", "Marketing", 20000, sdf.parse("2025-12-31")));
            addEmployee(new RegularEmployee("E004", "Bramanandam", "Finance", 55000, 3000));
            addEmployee(new Manager("E005", "Munu Kalu", "Operations", 75000, 12000));
            System.out.println("Sample data generated with " + employees.size() + " employees.");
        } catch (Exception e) {
            System.out.println("Error creating sample data: " + e.getMessage());
        }
    }
}