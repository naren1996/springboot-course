import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Scanner sc = new Scanner(System.in);
//        // --------------- CREATE1 ---------------
//        Transaction tx1 = session.beginTransaction();
//
//        Department dept = new Department("IT");
//        Employee e1 = new Employee("Rahul", 50000);
//        Employee e2 = new Employee("Priya", 60000);
//
//        dept.addEmployee(e1);
//        dept.addEmployee(e2);
//
//        session.persist(dept); // cascade से Employees भी save हो जाएंगे
//
//        Employee e3 = new Employee("Naren", 70000);
//        Employee e4 = new Employee("Amit", 70000);
//
//        dept.addEmployee(e3);
//        dept.addEmployee(e4);
//
//        session.persist(dept); // cascade से Employees भी save हो जाएंगे
//        tx1.commit();
//        System.out.println("✅ Department and Employees saved!");

        // --------------- CREATE2 ---------------
//        Transaction tx2 = session.beginTransaction();

//        Department dept = new Department("CSE");
//        Employee e1 = new Employee("Mamta", 80000);
//        Employee e2 = new Employee("Neha", 65000);
//
//        dept.addEmployee(e1);
//        dept.addEmployee(e2);
//
//        session.persist(dept); // cascade से Employees भी save हो जाएंगे
//
//        Employee e3 = new Employee("Rohit", 75000);
//        Employee e4 = new Employee("Karan", 76000);
//
//        dept.addEmployee(e3);
//        dept.addEmployee(e4);
//
//        session.persist(dept); // cascade से Employees भी save हो जाएंगे
//        tx2.commit();
//        System.out.println("✅ Department and Employees saved!");


        // --------------- ✅CREATE3 using console input---------------
//        Scanner sc = new Scanner(System.in);
//
//        // ---- Input Department ----
//        System.out.print("Enter Department Name: ");
//        String deptName = sc.nextLine();
//        Department dept = new Department(deptName);
//
//        // ---- Input Employees ----
//        System.out.print("How many employees you want to add? ");
//        int empCount = sc.nextInt();
//        sc.nextLine(); // consume newline
//
//        for (int i = 1; i <= empCount; i++) {
//            System.out.println("\n--- Enter Employee " + i + " Details ---");
//            System.out.print("Enter Employee Name: ");
//            String empName = sc.nextLine();
//
//            System.out.print("Enter Employee Salary: ");
//            double salary = sc.nextDouble();
//            sc.nextLine(); // consume newline
//
//            Employee emp = new Employee(empName, salary);
//            dept.addEmployee(emp);
//        }
//
//        // ---- Save in DB ----
//        Transaction tx1 = session.beginTransaction();
//        session.persist(dept);  // cascade से employees भी save हो जाएंगे
//        tx1.commit();
//
//        System.out.println("\n✅ Department and Employees saved!");


//        // --------------- ✅READ 1---------------
        // READ ALL Departments and Employees
//        List<Department> departments = session.createQuery("FROM Department", Department.class).list();
//
//        if (!departments.isEmpty()) {
//            for (Department d : departments) {
//                System.out.println("Department: " + d.getId() + " | " + d.getName());
//
//                if (!d.getEmployees().isEmpty()) {
//                    System.out.println("Employees:");
//                    for (Employee e : d.getEmployees()) {
//                        System.out.println("   -> " + e.getId() + " | " + e.getName() + " | Salary: " + e.getSalary());
//                    }
//                } else {
//                    System.out.println("   ❌ No Employees in this Department");
//                }
//                System.out.println("------------------------------------");
//            }
//        } else {
//            System.out.println("❌ No Departments Found!");
//        }


        // --------------- ✅READ 2 by Department id---------------
//        System.out.print("Enter Department Id to fetch: ");
//        int deptId = sc.nextInt();
//
//        // Hibernate से Department fetch करो
//        Department d = session.get(Department.class, deptId);
//
//        if (d != null) {
//            System.out.println("Department: " + d.getName());
//            for (Employee e : d.getEmployees()) {
//                System.out.println(" -> " + e.getName() + " | Salary: " + e.getSalary());
//            }
//        } else {
//            System.out.println("❌ Department not found!");
//        }


        // --------------- ✅READ 3 by Employee name including Department Table not all employees---------------
//        System.out.print("Enter Employee Name to search: ");
//        String empName = sc.nextLine();
//
//        Query<Employee> query = session.createQuery(
//                "FROM Employee e WHERE e.name = :name", Employee.class);
//        query.setParameter("name", empName);
//
//        List<Employee> employees = query.getResultList();
//
//        if (!employees.isEmpty()) {
//            for (Employee e : employees) {
//                System.out.println("Employee Found: "
//                        + e.getId() + " | " + e.getName() + " | " + e.getSalary());
//
//                // Department info bhi print karna
//                Department dept = e.getDepartment();
//                if (dept != null) {
//                    System.out.println("   Department: "
//                            + dept.getId() + " | " + dept.getName());
//                } else {
//                    System.out.println(" ❌ No Department assigned");
//                }
//            }
//        } else {
//            System.out.println("❌ No employee found with name " + empName);
//        }



        // ---------------✅ READ 4 by Employee id without Department table---------------

//        System.out.print("Enter Employee ID to search: ");
//        int empId = sc.nextInt();
//
//        Employee emp = session.get(Employee.class, empId);
//        if (emp != null) {
//            System.out.println("Employee Found: " + emp.getName() + " | Salary: " + emp.getSalary() + "| Department Id: " + emp.getDepartment());
//        } else {
//            System.out.println("❌ Employee not found!");
//        }



        // --------------- ✅READ 5 by Employee id with Department table with all employees---------------
//        System.out.print("Enter Employee ID to search: ");
//        int empId = sc.nextInt();
//
//        Employee emp = session.get(Employee.class, empId);
//
//        if (emp != null) {
//            System.out.println("Employee Found: "
//                    + emp.getId() + " | " + emp.getName()
//                    + " | Salary: " + emp.getSalary());
//
//            Department dept = emp.getDepartment();
//            if (dept != null) {
//                System.out.println("   Department: " + dept.getId() + " | " + dept.getName());
//
//                // Department ke employees list bhi print karne ke liye
//                System.out.println("   Employees in this Department:");
//                for (Employee e : dept.getEmployees()) {
//                    System.out.println("      -> " + e.getId() + " | " + e.getName() + " | " + e.getSalary());
//                }
//            } else {
//                System.out.println(" ❌ No Department assigned");
//            }
//        } else {
//            System.out.println("❌ Employee not found!");
//        }



        // ✅Read by Department name including Employee Table
//        System.out.print("Enter Department Name to search: ");
//        String deptName = sc.nextLine();
//
//        Query<Department> q = session.createQuery("FROM Department d WHERE d.name = :name", Department.class);
//        q.setParameter("name", deptName);
//        Department dept = q.uniqueResult();
//
//        if (dept != null) {
//            System.out.println("Department: " + dept.getName()+ ", " +"Department Id: " + dept.getId());
//            for (Employee e : dept.getEmployees()) {
//                System.out.println(" EmployeeId-> " + e.getId() + ","+ "Employee Name ->  " + e.getName() + " , " +  " | Salary: " + e.getSalary());
//            }
//        } else {
//            System.out.println("❌ Department not found!");
//        }


        //✅Read by Department name not EMPLOYEE table
//        System.out.print("Enter Department Name to search: ");
//        String deptName = sc.nextLine();
//
//        Query<Department> q = session.createQuery("FROM Department d WHERE d.name = :name", Department.class);
//        q.setParameter("name", deptName);
//        Department dept = q.uniqueResult();
//
//        if (dept != null) {
//            System.out.println("Department: " + dept.getName()+ ", " +"Department Id: " + dept.getId());
//
//        } else {
//            System.out.println("❌ Department not found!");
//        }




//        // --------------- ✅UPDATE By Manual---------------
        // READ Department first
        Department d = session.get(Department.class, 3);
        System.out.println("Department: " + d.getName());
        Transaction tx2 = session.beginTransaction();
        Employee empToUpdate = d.getEmployees().get(0); // list me first employee{Ayush , Gungun} so updated Ayush salary
        empToUpdate.setSalary(70000); // salary update
        tx2.commit();
        System.out.println("✅ Employee salary updated!");



        //✅UPDATE 2 to change salary
//        System.out.print("Enter Employee ID to update salary: ");
//        int empId = sc.nextInt();
//        System.out.print("Enter new salary: ");
//        double newSalary = sc.nextDouble();
//
//        Transaction tx2 = session.beginTransaction();
//
//        // Employee fetch
//        Employee empToUpdate = session.get(Employee.class, empId);
//
//        if (empToUpdate != null) {
//            empToUpdate.setSalary(newSalary); // update salary
//            session.merge(empToUpdate);      // ensure update
//            tx2.commit();
//            System.out.println("✅ Salary updated for Employee: "
//                    + empToUpdate.getName() + " | New Salary: " + empToUpdate.getSalary());
//        } else {
//            System.out.println("❌ Employee not found with ID " + empId);
//            tx2.rollback();
//        }




        //✅Want to change Department for existing employee
//        System.out.print("Enter Employee ID to change department: ");
//        int empId = sc.nextInt();
//        System.out.print("Enter New Department ID: ");
//        int deptId = sc.nextInt();
//
//        Transaction tx = session.beginTransaction();
//
//        // Fetch employee
//        Employee emp = session.get(Employee.class, empId);
//        // Fetch new department
//        Department newDept = session.get(Department.class, deptId);
//
//        if (emp != null && newDept != null) {
//            emp.setDepartment(newDept);  // foreign key update
//            session.merge(emp);          // update in DB
//            tx.commit();
//            System.out.println("✅ Employee " + emp.getName() + " moved to Department: " + newDept.getName());
//        } else {
//            System.out.println("❌ Invalid Employee ID or Department ID!");
//            tx.rollback();
//        }



//        // --------------- ✅DELETE By Manual---------------


        // READ Department first
//        Department d = session.get(Department.class, 3);  // मान लो Id = 1 है
//        System.out.println("Department: " + d.getName());
//
//        ----------- DELETE Example -----------
//        Transaction tx3 = session.beginTransaction();
//
//        Employee empToDelete = d.getEmployees().get(0);   // list me indexing k according {Rahul,Mona,Mani}
//        d.getEmployees().remove(empToDelete);
//        session.remove(empToDelete);  // Hibernate delete
//
//
//        tx3.commit();
//        System.out.println("✅ Employee deleted!");




        // -----✅DELETE by DEPARTMENT ID BY USER CONSOLE
      // Step 1: Department ID input lo
//        System.out.print("Enter Department ID: ");
//        int deptId = sc.nextInt();
//
//       // Department fetch karo
//        Department dept = session.get(Department.class, deptId);
//
//        if (dept != null) {
//            System.out.println("Department Found: " + dept.getName());
//            System.out.println("Employees in Department:");
//
//            for (Employee e : dept.getEmployees()) {
//                System.out.println(" -> ID: " + e.getId() + " | Name: " + e.getName() + " | Salary: " + e.getSalary());
//            }
//
//            // Step 2: Employee ID input lo
//            System.out.print("Enter Employee ID to delete: ");
//            int empId = sc.nextInt();
//
//            Transaction tx = session.beginTransaction();
//
//            Employee empToDelete = session.get(Employee.class, empId);
//
//            if (empToDelete != null && empToDelete.getDepartment().getId() == deptId) {
//                // Department list se bhi remove karna zaroori hai
//                dept.getEmployees().remove(empToDelete);
//
//                session.remove(empToDelete);
//                tx.commit();
//                System.out.println("✅ Employee deleted successfully!");
//            } else {
//                System.out.println("❌ Employee not found in this department!");
//                tx.rollback();
//            }
//
//        } else {
//            System.out.println("❌ Department not found!");
//        }




        //--------✅Delete by Employee id--------

        // Employee ID input from console
//        System.out.print("Enter Employee ID to delete: ");
//        int empId = sc.nextInt();
//
//        Transaction tx = session.beginTransaction();
//
//        // Step 1: उस ID से employee fetch करो
//        Employee empToDelete = session.get(Employee.class, empId);
//
//        if (empToDelete != null) {
//            // Step 2: पहले department की list से remove करो (consistency के लिए)
//            Department dept = empToDelete.getDepartment();
//            if (dept != null) {
//                dept.getEmployees().remove(empToDelete);
//            }
//
//            // Step 3: अब Hibernate को बोलो database से delete करने के लिए
//            session.remove(empToDelete);
//
//            tx.commit();
//            System.out.println("✅ Employee deleted successfully!");
//        } else {
//            System.out.println("❌ Employee not found with ID: " + empId);
//        }



        session.close();
        sessionFactory.close();
        sc.close();
    }
}

