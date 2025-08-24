import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Scanner sc = new Scanner(System.in);

        // ----- Step 1: Insert fixed courses (only once) -----
        Transaction txInit = session.beginTransaction();

        if (session.createQuery("FROM Course", Course.class).list().isEmpty()) {
            session.persist(new Course("Java Programming", "Learn core Java concepts"));
            session.persist(new Course("Web Development", "HTML, CSS, JS, React basics"));
            session.persist(new Course("Spring Boot", "Build REST APIs with Spring Boot"));
            session.persist(new Course("Database Systems", "Learn MySQL and Hibernate"));
            session.persist(new Course("Cloud Computing", "Introduction to AWS and Azure"));
            System.out.println("✅ Predefined courses added!");
        }

        txInit.commit();

        int choice;
        do {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Courses");
            System.out.println("3. Enroll Student to Course");
            System.out.println("4. View Student Enrollments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> {
                    // Add new student
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Student Email: ");
                    String email = sc.nextLine();

                    Transaction tx1 = session.beginTransaction();
                    Students s = new Students(name, email);
                    session.persist(s);
                    tx1.commit();
                    System.out.println("✅ Student Added: " + s);
                }
                case 2 -> {
                    // View all courses
                    List<Course> courses = session.createQuery("FROM Course", Course.class).list();
                    System.out.println("📚 Available Courses:");
                    for (Course c : courses) {
                        System.out.println(c.getId() + " - " + c.getTitle() + " (" + c.getDescription() + ")");
                    }
                }
                case 3 -> {
                    // Enroll student into course
                    System.out.print("Enter Student Email: ");
                    String email = sc.nextLine();

                    Students student = session.createQuery("FROM Student WHERE email = :email", Students.class)
                            .setParameter("email", email)
                            .uniqueResult();

                    if (student == null) {
                        System.out.println("❌ Student not found! Please add student first.");
                        break;
                    }

                    System.out.print("Enter Course ID: ");
                    Long cid = sc.nextLong();

                    Course course = session.get(Course.class, cid);
                    if (course == null) {
                        System.out.println("❌ Course not found!");
                        break;
                    }

                    Enrollment enrollment = new Enrollment(student, course, LocalDate.now());

                    Transaction tx2 = session.beginTransaction();
                    session.persist(enrollment);
                    tx2.commit();

                    System.out.println("✅ Student Enrolled: " + enrollment);
                }
                case 4 -> {
                    // View all enrollments of a student
                    System.out.print("Enter Student Email: ");
                    String email = sc.nextLine();

                    Students student = session.createQuery("FROM Student WHERE email = :email", Students.class)
                            .setParameter("email", email)
                            .uniqueResult();

                    if (student == null) {
                        System.out.println("❌ Student not found!");
                        break;
                    }

                    List<Enrollment> enrollments = session.createQuery("FROM Enrollment WHERE student.id = :sid", Enrollment.class)
                            .setParameter("sid", student.getId())
                            .list();

                    System.out.println("📖 Enrollments of " + student.getName() + ":");
                    for (Enrollment e : enrollments) {
                        System.out.println("Course: " + e.getCourse().getTitle() +
                                " | Date: " + e.getEnrollmentDate());

                    }
                }
                case 0 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❌ Invalid choice!");
            }

        } while (choice != 0);

        session.close();
        factory.close();
        sc.close();

    }
}
