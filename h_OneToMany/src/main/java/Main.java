import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Phone.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Scanner sc = new Scanner(System.in);

//        // ------------------ CREATE1 ------------------
//        Transaction tx = session.beginTransaction();
//
//        Student student = new Student("Amit Kumar", "amit@example.com");
//
//        Phone phone1 = new Phone("9876543210", "Mobile");
//        Phone phone2 = new Phone("0612-223344", "Home");
//
//        student.addPhone(phone1);
//        student.addPhone(phone2);
//
//        session.persist(student);
//
//        tx.commit();
//        System.out.println("✅ Student and Phones Saved!");

//        // ------------------ CREATE ------------------
//        Transaction tx = session.beginTransaction();
//
//        Student student2 = new Student("Narender Kumar", "naren96@example.com");
//
//        Phone phone1 = new Phone("7786334521", "Mobile");
//        Phone phone2 = new Phone("0612-87546", "Work");
//
//        student2.addPhone(phone1);
//        student2.addPhone(phone2);
//
//        session.persist(student2);
//
//        tx.commit();
//        System.out.println("✅ Student and Phones Saved!");

        // ---------------- CREATE Student with Phone(s) ----------------
//        System.out.print("Enter Student Name: ");
//        String name = sc.nextLine();
//
//        System.out.print("Enter Student Email: ");
//        String email = sc.nextLine();
//
//        Student student = new Student(name, email);
//
//        System.out.print("How many phone numbers you want to add? ");
//        int n = sc.nextInt();
//        sc.nextLine(); // consume newline
//
//        for (int i = 0; i < n; i++) {
//            System.out.print("Enter phone number " + (i+1) + ": ");
//            String phoneNo = sc.nextLine();
//
//            System.out.print("Enter phone type (Home/Work/Other) " + (i+1) + ": ");
//            String type = sc.nextLine();
//
//            // ✅ अब सही constructor कॉल करेंगे
//            Phone phone = new Phone(phoneNo, type);
//            student.addPhone(phone);
//        }
//
//        // Hibernate transaction
//        Transaction tx = session.beginTransaction();
//        session.persist(student);
//        tx.commit();
//
//        System.out.println("✅ Student and Phones saved successfully!");



        // ------------------ READ ------------------
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter Student ID to fetch: ");
//        int studentId = sc.nextInt();
//
//        Student student = session.get(Student.class, studentId);
//
//        if (student != null) {
//            System.out.println("Fetched Student: " + student.getName());
//            for (Phone p : student.getPhones()) {
//                System.out.println(" -> " + p);
//            }
//        } else {
//            System.out.println("No student found with id: " + studentId);
//        }

        // ------------------ READ ALL ------------------
        List<Student> students = session.createQuery(
                "FROM Student", Student.class).getResultList();

        System.out.println("===== All Students and Phones =====");
        for (Student s : students) {
            System.out.println("Student: " + s.getName() + ", Email: " + s.getEmail());
            if (s.getPhones() != null && !s.getPhones().isEmpty()) {
                for (Phone p : s.getPhones()) {
                    System.out.println("   -> Phone: " + p.getNumber() + " (" + p.getType() + ")");
                }
            } else {
                System.out.println("   -> No Phones Saved");
            }
        }

//        // ------------------ UPDATE 1------------------
        // पहले student को fetch करो
//        int studentId = 1; // जिस student का data update करना है
//        Student student = session.get(Student.class, studentId);
//
//        if (student != null && !student.getPhones().isEmpty()) {
//            Transaction tx2 = session.beginTransaction();
//
//            // पहला phone object लो
//            Phone phoneToUpdate = student.getPhones().get(0);
//            phoneToUpdate.setNumber("9999999999");  // नया number set करो
//
//            session.update(phoneToUpdate); // Hibernate को बताना पड़ेगा कि यह update है
//            tx2.commit();
//
//            System.out.println("✅ Phone Updated!");
//        } else {
//            System.out.println("❌ Student not found or no phones available!");
//        }




        // ------------------ UPDATE 2------------------
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter Student ID: ");
//        int studentId = sc.nextInt();
//
//        Student student = session.get(Student.class, studentId);
//
//        if (student != null && !student.getPhones().isEmpty()) {
//            System.out.println("Phones of " + student.getName() + ":");
//            int i = 1;
//            for (Phone p : student.getPhones()) {
//                System.out.println(i + ". " + p.getNumber());
//                i++;
//            }
//
//            System.out.print("Which phone do you want to update (index): ");
//            int choice = sc.nextInt();
//            sc.nextLine(); // consume enter
//
//            if (choice > 0 && choice <= student.getPhones().size()) {
//                Phone phoneToUpdate = student.getPhones().get(choice - 1);
//
//                System.out.print("Enter new phone number: ");
//                String newNumber = sc.nextLine();
//
//                Transaction tx2 = session.beginTransaction();
//                phoneToUpdate.setNumber(newNumber);
//                session.update(phoneToUpdate);
//                tx2.commit();
//
//                System.out.println("✅ Phone Updated Successfully!");
//            } else {
//                System.out.println("❌ Invalid choice!");
//            }
//        } else {
//            System.out.println("❌ Student not found or no phones available!");
//        }

//        // ------------------ DELETE 1------------------
//        Transaction tx3 = session.beginTransaction();
//        Phone phoneToDelete = s.getPhones().get(1);
//        s.removePhone(phoneToDelete);   // helper method
//        tx3.commit();
//        System.out.println("✅ Phone Deleted!");

        // ------------------ DELETE 2 by Phone ID------------------
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter Phone ID to delete: ");
//        int phoneId = sc.nextInt();
//
//        Transaction tx = session.beginTransaction();
//        Phone phoneToDelete = session.get(Phone.class, phoneId);
//
//        if (phoneToDelete != null) {
//            session.delete(phoneToDelete);
//            System.out.println("✅ Phone Deleted Successfully!");
//        } else {
//            System.out.println("❌ Phone not found!");
//        }
//
//        tx.commit();

        // ---------------- DELETE by Student Name ----------------
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter Student name to delete phones: ");
//        String studentName = sc.nextLine();
//
//        Transaction tx = session.beginTransaction();
//
//        // HQL query to delete Phones of a given Student name
//        String hql = "delete from Phone p where p.student.name = :name";
//        int rows = session.createQuery(hql)
//                .setParameter("name", studentName)
//                .executeUpdate();
//
//        tx.commit();
//
//        if (rows > 0) {
//            System.out.println("✅ Deleted " + rows + " phone(s) of student: " + studentName);
//        } else {
//            System.out.println("❌ No phones found for student: " + studentName);
//        }

        // ---------------- DELETE Students with NO Phones ----------------
//        Transaction tx = session.beginTransaction();
//
//        String hql = "delete from Student s where s.phones is empty";
//        int rows = session.createQuery(hql).executeUpdate();
//
//        tx.commit();
//
//        if (rows > 0) {
//            System.out.println("✅ Deleted " + rows + " student(s) who have no phone numbers.");
//        } else {
//            System.out.println("❌ No students found without phones.");
//        }

        session.close();
        factory.close();
    }
}
