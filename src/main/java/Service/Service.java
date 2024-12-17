package Service;

import Entity.Company;
import Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Service {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("swati");
    private static Scanner scanner = new Scanner(System.in); 

  
    public static void save() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

       

        System.out.print("What do you want to save? (1 for Company, 2 for Employee): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice == 1) {
            System.out.print("Enter Company Name: ");
            String companyName = scanner.nextLine();
            Company company = new Company();
            company.setCName(companyName);
            em.persist(company);
        } else if (choice == 2) {
            System.out.print("Enter Employee Name: ");
            String empName = scanner.nextLine();
            System.out.print("Enter Employee Email: ");
            String empEmail = scanner.nextLine();
            System.out.print("Enter Employee Salary: ");
            int empSalary = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter Company Name: ");
            String companyName = scanner.nextLine();
            Company company = new Company();
            company.setCName(companyName);

            Employee employee = new Employee();
            employee.setEmpName(empName);
            employee.setEmail(empEmail);
            employee.setSalary(empSalary);
            employee.setCompany(company);
            et.begin();
            em.persist(company);
            em.persist(employee);
            et.commit();
        } else {
            System.out.println("Invalid choice. Please choose 1 for Company or 2 for Employee.");
        }

        

        System.out.println("Entity saved successfully.");
    }


    public static void delete() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        
        System.out.println("What do you want to delete?");
        System.out.println("1. Company");
        System.out.println("2. Employee");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            System.out.print("Enter Company ID to delete: ");
            int companyId = scanner.nextInt();
            scanner.nextLine();
            
            Company company = em.find(Company.class, companyId);
            if (company != null) {
                em.remove(company);
                System.out.println("Company deleted successfully.");
            } else {
                System.out.println("Company not found with ID: " + companyId);
            }
        } else if (choice == 2) {
            System.out.print("Enter Employee ID to delete: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine();
            
            Employee employee = em.find(Employee.class, employeeId);
            if (employee != null) {
                em.remove(employee);
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found with ID: " + employeeId);
            }
        } else {
            System.out.println("Invalid choice. Please enter 1 for Company or 2 for Employee.");
        }
        
        et.commit();
    }

   
    public static void fetch() {
        EntityManager em = emf.createEntityManager();
        
        
        System.out.print("Enter Company ID to fetch: ");
        int companyId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        
        Company company = em.find(Company.class, companyId);  
        if (company != null) {
            System.out.println("Company ID: " + company.getCid());
            System.out.println("Company Name: " + company.getCName());
            
           
            if (company.getEmp() != null && !company.getEmp().isEmpty()) {
                for (Employee employee : company.getEmp()) {
                    System.out.println("Employee ID: " + employee.getEmpId());
                    System.out.println("Employee Name: " + employee.getEmpName());
                    System.out.println("Employee Email: " + employee.getEmail());
                    System.out.println("Employee Salary: " + employee.getSalary());
                }
            } else {
                System.out.println("No employees found for this company.");
            }
        } else {
            System.out.println("Company not found with ID: " + companyId);
        }
        
        
    }
    public static void update() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();

        System.out.println("What would you like to update?");
        System.out.println("1. Company details");
        System.out.println("2. Employee details");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            System.out.print("Enter Company ID to update: ");
            int companyId = scanner.nextInt();
            scanner.nextLine();

            Company company = em.find(Company.class, companyId);
            if (company != null) {
                System.out.println("What would you like to update in the company?");
                System.out.println("1. Company Name");
                System.out.print("Enter choice (1): ");
                int updateChoice = scanner.nextInt();
                scanner.nextLine();
                
                if (updateChoice == 1) {
                    System.out.print("Enter new Company Name: ");
                    String newCompanyName = scanner.nextLine();
                    company.setCName(newCompanyName);
                    em.merge(company);
                    System.out.println("Company name updated successfully.");
                } else {
                    System.out.println("Invalid choice. No changes made.");
                }

                et.commit();
            } else {
                System.out.println("Company not found with ID: " + companyId);
            }
        } else if (choice == 2) {
            System.out.print("Enter Employee ID to update: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine();

            Employee employee = em.find(Employee.class, employeeId);
            if (employee != null) {
                System.out.println("What would you like to update in the employee?");
                System.out.println("1. Employee Name");
                System.out.println("2. Employee Email");
                System.out.println("3. Employee Salary");
                System.out.print("Enter choice (1, 2, or 3): ");
                int updateChoice = scanner.nextInt();
                scanner.nextLine();
                
                if (updateChoice == 1) {
                    System.out.print("Enter new Employee Name: ");
                    String newEmpName = scanner.nextLine();
                    employee.setEmpName(newEmpName);
                    System.out.println("Employee name updated successfully.");
                } else if (updateChoice == 2) {
                    System.out.print("Enter new Employee Email: ");
                    String newEmpEmail = scanner.nextLine();
                    employee.setEmail(newEmpEmail);
                    System.out.println("Employee email updated successfully.");
                } else if (updateChoice == 3) {
                    System.out.print("Enter new Employee Salary: ");
                    int newEmpSalary = scanner.nextInt();
                    scanner.nextLine();
                    employee.setSalary(newEmpSalary);
                    System.out.println("Employee salary updated successfully.");
                } else {
                    System.out.println("Invalid choice. No changes made.");
                }

                em.merge(employee);
                et.commit();
            } else {
                System.out.println("Employee not found with ID: " + employeeId);
            }
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

    }


}
