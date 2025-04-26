import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

// Employee Management System class
public class EmployeeManagementSystem {
    private List<Employee> employees; // Using List interface for polymorphism
    private String dataFile;

    public EmployeeManagementSystem(String dataFile) {
        this.dataFile = dataFile;
        this.employees = new ArrayList<>(); // Using ArrayList for efficient random access
    }

    // Load employees from file
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

    // Save employees to file
    public void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(employees);
            System.out.println("Employee data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    // Add a new employee
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully: " + employee.getName());
    }

    // Remove an employee by ID
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

    // Find employee by ID
    public Employee findEmployeeById(String id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                return emp;
            }
        }
        return null;
    }

    // Find employees by name (could be multiple)
    public List<Employee> findEmployeesByName(String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                result.add(emp);
            }
        }
        return result;
    }

    // Find employees by performance rating
    public List<Employee> findEmployeesByPerformance(int rating) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getPerformanceRating() == rating) {
                result.add(emp);
            }
        }
        return result;
    }

    // List all employees
    public void listAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
            return;
        }
        System.out.println("\nList of all employees:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    // Update employee information
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

    // Generate sample data
    public void generateSampleData() {
        // Clear existing data
        employees.clear();
        
        // Add sample employees
        addEmployee(new RegularEmployee("E001", "John Doe", "IT", 50000));
        addEmployee(new Manager("E002", "Jane Smith", "HR", 70000));
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = sdf.parse("2023-12-31");
            addEmployee(new Intern("E003", "Mike Johnson", "Marketing", 20000, endDate));
        } catch (Exception e) {
            System.out.println("Error creating sample data: " + e.getMessage());
        }
        
        // Add more sample employees as needed
        addEmployee(new RegularEmployee("E004", "Alice Brown", "Finance", 55000));
        addEmployee(new Manager("E005", "Bob Wilson", "Operations", 75000));
        
        System.out.println("Sample data generated with " + employees.size() + " employees.");
    }
}