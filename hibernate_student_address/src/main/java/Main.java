import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        // Create Address
//        Address addr = new Address();
//        addr.setCity("Patna");
//        addr.setState("Bihar");
//
//        // Create Student
//        Student st = new Student();
//        st.setName("Suman");
//        st.setAddress(addr); // link student with address
//
//        // User 2
//        Address a2 = new Address();
//        a2.setCity("Ranchi");
//        a2.setState("Jharkhand");
//
//        Student st2 = new Student();
//        st2.setName("Anjali");
//        st2.setAddress(a2);
//
//        // User 3
//        Address a3 = new Address();
//        a3.setCity("Delhi");
//        a3.setState("Delhi");
//
//        Student st3 = new Student();
//        st3.setName("Rahul");
//        st3.setAddress(a3);

        // User 4
        Address a4 = new Address();
        a4.setCity("Pune");
        a4.setState("Maharashtra");

        Student st4 = new Student();
        st4.setName("Narender");
        st4.setAddress(a4);

//        session.save(st);
//        session.save(st2);
//        session.save(st3);
        session.save(st4);


        tx.commit();
        session.close();
        sessionFactory.close();

        System.out.println("✅ Student and Address saved with foreign key!");
    }
}
