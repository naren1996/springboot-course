import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RecruiterDAO {

    private SessionFactory sessionFactory;

    public RecruiterDAO() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    // SIGNUP (Create Recruiter)
    public void signup(Recruiter recruiter) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(recruiter);
        tx.commit();
        session.close();
        System.out.println("✅ Recruiter signed up successfully!");
    }

    // LOGIN (Check email & password)
    public boolean login(String email, String password) {
        Session session = sessionFactory.openSession();
        Recruiter recruiter = session.createQuery(
                        "FROM Recruiter WHERE email = :email", Recruiter.class)
                .setParameter("email", email)
                .uniqueResult();
        session.close();

        if (recruiter != null && recruiter.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    // READ by Id
//    public Recruiter getRecruiterById(Long recruiterId) {
//        Session session = sessionFactory.openSession();
//        Recruiter recruiter = session.get(Recruiter.class, recruiterId);
//        session.close();
//        return recruiter;
//    }
    public void showJobsByRecruiter(Long recruiterId) {
        Session session = sessionFactory.openSession();

        Recruiter recruiter = session.createQuery(
                        "SELECT r FROM Recruiter r LEFT JOIN FETCH r.jobs WHERE r.recruiterId = :id",
                        Recruiter.class)
                .setParameter("id", recruiterId)
                .uniqueResult();

        session.close();

        if (recruiter != null) {
            System.out.println("\nRecruiter: " + recruiter.getName() + " | Company: " + recruiter.getCompany());
            System.out.println("Jobs Posted:");

            if (recruiter.getJobs().isEmpty()) {
                System.out.println("❌ No jobs posted yet.");
            } else {
                for (Job job : recruiter.getJobs()) {
                    System.out.println(" - " + job.getTitle() + " | Skill: " + job.getSkill().getSkillName());
                }
            }
        } else {
            System.out.println("❌ Recruiter not found!");
        }
    }


}
