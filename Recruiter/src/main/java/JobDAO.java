import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class JobDAO {

    private SessionFactory sessionFactory;

    public JobDAO() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    // CREATE
    public void addJob(Job job) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(job);
        tx.commit();
        session.close();
        System.out.println("✅ Job added successfully!");
    }

    // READ
    public Job getJobById(Long jobId) {
        Session session = sessionFactory.openSession();
        Job job = session.get(Job.class, jobId);
        session.close();
        return job;
    }

    // UPDATE
    public void updateJob(Job job) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(job);
        tx.commit();
        session.close();
        System.out.println("✅ Job updated successfully!");
    }

    // DELETE
    public void deleteJob(Long jobId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Job job = session.get(Job.class, jobId);
        if (job != null) {
            session.delete(job);
            System.out.println("✅ Job deleted successfully!");
        } else {
            System.out.println("❌ Job not found!");
        }
        tx.commit();
        session.close();
    }
}

