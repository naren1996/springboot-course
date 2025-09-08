import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SkillDAO {

    private SessionFactory sessionFactory;

    public SkillDAO() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    // CREATE
    public void addSkill(Skill skill) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(skill);
        tx.commit();
        session.close();
        System.out.println("✅ Skill added successfully!");
    }

    // READ
    public Skill getSkillById(Long skillId) {
        Session session = sessionFactory.openSession();
        Skill skill = session.get(Skill.class, skillId);
        session.close();
        return skill;
    }

    // UPDATE
    public void updateSkill(Skill skill) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(skill);
        tx.commit();
        session.close();
        System.out.println("✅ Skill updated successfully!");
    }

    // DELETE
    public void deleteSkill(Long skillId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Skill skill = session.get(Skill.class, skillId);
        if (skill != null) {
            session.delete(skill);
            System.out.println("✅ Skill deleted successfully!");
        } else {
            System.out.println("❌ Skill not found!");
        }
        tx.commit();
        session.close();
    }
}

