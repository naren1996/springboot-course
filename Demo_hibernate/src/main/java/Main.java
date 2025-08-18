import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        //1: load configuration
        Configuration config = new Configuration().configure("hibernate.cfg.xml");

        //2: create Session factory
        SessionFactory sessionFactory = config.buildSessionFactory();

        //3: open session
        Session session = sessionFactory.openSession();

        // 4: begin transaction
        Transaction transaction = session.beginTransaction();
//        User user = new User();
//        user.setId(1);
//        user.setName("Suman");
//        user.setAddress("Bengalore");
//
//        session.save(user);

//        User user2 = new User();
//        user2.setId(2);
//        user2.setName("Suman");
//        user2.setAddress("Bangalore");
//
//        session.save(user2);

//        User user3 = new User();
//        user3.setId(3);
//        user3.setName("Naren");
//        user3.setAddress("Asansol");
//
//        session.save(user3);
//
//        User user4 = new User();
//        user4.setId(4);
//        user4.setName("Pawan");
//        user4.setAddress("Patna");
//
//        session.save(user4);


        // Fetch user from DB (id = 1)
//        User user = session.get(User.class, 2);
//        if (user != null) {
//            user.setName("Rahul");
//            user.setAddress("Delhi");
//
//            session.update(user); // Updating object
//        }

    //Fetch data using not primary key
        // HQL Query
//        String hql = "FROM User WHERE name = :name";
//        User user = session.createQuery(hql, User.class)
//                .setParameter("name", "Suman")
//                .uniqueResult();
//
//        if (user != null) {
//            System.out.println("User Found: " + user.getId() + " - " + user.getName() + " - " + user.getAddress());
//        } else {
//            System.out.println("User not found!");
//        }

        //using SQl query
//        String sql = "SELECT * FROM user_table WHERE name = :name";
//        User user = session.createNativeQuery(sql, User.class).setParameter("name", "Suman").uniqueResult();

//        String sql = "SELECT * FROM user_table WHERE address = :address";
//         User user = session.createNativeQuery(sql, User.class).setParameter("address", "Pune").uniqueResult();
//           if (user != null) {
//            System.out.println("User Found: " + user.getId() + " - " + user.getName() + " - " + user.getAddress());
//           } else {
//           System.out.println("User not found!");
//        }

        String hql = "DELETE FROM User WHERE name = :name";
        int rowsAffected = session.createQuery(hql)
                .setParameter("name", "Pawan")
                .executeUpdate();

        System.out.println("Rows deleted: " + rowsAffected);

        //Delete data using SQl
//        User user = session.get(User.class, 1);  // Delete user with id=1
//        if (user != null) {
//            session.delete(user);
//            System.out.println("User deleted successfully!");
//        } else {
//            System.out.println("User not found!");
//        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
