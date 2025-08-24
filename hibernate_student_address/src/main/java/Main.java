import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1: Configure Hibernate
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Address.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        // Create Address
      //  Address address = new Address("MG Road", "Bengaluru", "560001");

        // Create Student with Address
       // Student student = new Student("Amit Kumar", "amit@example.com", address);

        // Save Student (Address will also be saved because of CascadeType.ALL)
       // session.persist(student);

        // Create Address
//        Address add2 = new Address("Asansol", "West Bengal", "713359");
//
//        // Create Student with Address
//        Student student2 = new Student("Rakesh Kumar", "rakesh22@example.com", add2);
//
//        // Save Student (Address will also be saved because of CascadeType.ALL)
//        session.persist(student2);

     //   2️⃣ Fetch All Students with Address
//        List<Student> students = session.createQuery("FROM Student", Student.class).list();
//        for (Student s : students) {
//            System.out.println(s.getName() + " lives at " + s.getAddress().getCity());
//        }

       // 3️⃣ Fetch Student by Name
//        Student student = session.createQuery(
//                        "FROM Student s WHERE s.name = :name", Student.class)
//                .setParameter("name", "Rohit Kumar")
//                .uniqueResult();
//
//        System.out.println("Found Student: " + student);

       // 4️⃣ Fetch Address Using Student Email (JOIN Example)
//        List<Address> addresses = session.createQuery(
//                        "SELECT s.address FROM Student s WHERE s.email = :email", Address.class)
//                .setParameter("email", "amit@example.com")
//                .list();
//
//        for (Address addr : addresses) {
//            System.out.println("Address: " + addr);
//        }

      //  5️⃣ Update Student’s Address via HQL
//        int updated = session.createQuery(
//                        "UPDATE Address a SET a.city = :city ,a.street = :street WHERE a.id = :id")
//                .setParameter("city", "Delhi")
//                .setParameter("street", "JP Road")
//                .setParameter("id", 1L)
//                .executeUpdate();
//
//        tx.commit();
//
//        System.out.println("Updated rows: " + updated);


       // 6️⃣ Delete Student by Name (Cascade → Address also deleted)
//        int deleted = session.createQuery(
//                        "DELETE FROM Student s WHERE s.name = :name")
//                .setParameter("name", "Amit Kumar")
//                .executeUpdate();
//
//        tx.commit();
//
//        System.out.println("Deleted rows: " + deleted);

       // 7️⃣ Fetch Student with Address (JOIN FETCH)
        List<Student> list = session.createQuery(
                "SELECT s FROM Student s JOIN FETCH s.address", Student.class).list();

        for (Student s : list) {
            System.out.println(s.getName() + " -> " + s.getAddress().getStreet());
        }

        tx.commit();
        session.close();
        sessionFactory.close();

        System.out.println("✅ Student and Address saved successfully!!");
    }
}
