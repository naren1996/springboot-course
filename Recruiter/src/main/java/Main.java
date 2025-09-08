import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RecruiterDAO recruiterDAO = new RecruiterDAO();
       // RecruiterDAO recruiterDAO = new RecruiterDAO();
        JobDAO jobDAO = new JobDAO();
        SkillDAO skillDAO = new SkillDAO();
        // SIGNUP (New Recruiter)
        Recruiter recruiter = new Recruiter();
        recruiter.setName("Narender Kumar");
        recruiter.setCompany("CloudKaptan");
        recruiter.setEmail("nk@gmail.com");
        recruiter.setPassword("12345");
        recruiter.setAddress("Asansol");

        recruiterDAO.signup(recruiter);

        // 2. Create skills
        Skill javaSkill = new Skill();
        javaSkill.setSkillName("Java Backend");
        skillDAO.addSkill(javaSkill);

        Skill reactSkill = new Skill();
        reactSkill.setSkillName("ReactJS");
        skillDAO.addSkill(reactSkill);

        // 3. Add multiple jobs for recruiter
        Job job1 = new Job();
        job1.setTitle("Java Developer");
        job1.setDescription("Work on Spring Boot and Hibernate");
        job1.setLocation("Pune,Kolkata,Bangalore");
        job1.setSalaryPackage("5LPA");
        // Set dates
        job1.setPostedDate(LocalDate.now()); // today's date
        job1.setExpiryDate(LocalDate.now().plusDays(30)); // expire after 30 days
        job1.setRecruiter(recruiter);
        job1.setSkill(javaSkill);
        jobDAO.addJob(job1);

        Job job2 = new Job();
        job2.setTitle("Frontend Developer");
        job2.setDescription("Work on ReactJS and UI/UX");
        job2.setLocation("Pune,Chennai,Bangalore");
        job2.setSalaryPackage("8LPA");
        // Set dates
        job2.setPostedDate(LocalDate.now()); // today's date
        job2.setExpiryDate(LocalDate.now().plusDays(30));
        job2.setRecruiter(recruiter);
        job2.setSkill(reactSkill);
        jobDAO.addJob(job2);

        // LOGIN test
//        boolean loginSuccess = recruiterDAO.login("suman11@gmail.com", "12345");
//        if (loginSuccess) {
//            System.out.println("✅ Login successful!");
//        } else {
//            System.out.println("❌ Invalid email or password!");
//        }
//
//       // RecruiterDAO recruiterDAO = new RecruiterDAO();
//        JobDAO jobDAO = new JobDAO();
//        SkillDAO skillDAO = new SkillDAO();

//        Skill skill = new Skill();
//        skill.setSkillName("MSword, Excel, MYSQL");
//        skillDAO.addSkill(skill);
//
//        Job job = new Job();
//        job.setTitle("Accountant");
//        job.setDescription("Work on Accounts project");
//        job.setRecruiter(recruiter);
//        job.setSkill(skill);
//        jobDAO.addJob(job);

//        Skill skill2 = new Skill();
//        skill2.setSkillName("Python, Docker, MYSQL");
//        skillDAO.addSkill(skill2);
//
//        Job job2 = new Job();
//        job2.setTitle("Python Developer");
//        job2.setDescription("Work on Jupyter Notebook");
//        job2.setRecruiter(recruiter);
//        job2.setSkill(skill2);
//        jobDAO.addJob(job2);

        // READ
//        Recruiter r = recruiterDAO.getRecruiterById(recruiter.getRecruiterId());
//        System.out.println("Recruiter: " + r.getName() + " | Company: " + r.getCompany());
//
//        Job j = jobDAO.getJobById(job.getJobId());
//        System.out.println("Job: " + j.getTitle() + " | Skill: " + j.getSkill().getSkillName());

        //Fetch Recruiter and their posted all jobs
        recruiterDAO.showJobsByRecruiter(4L);


//        // UPDATE
//        r.setCompany("Updated Company Pvt Ltd");
//        recruiterDAO.updateRecruiter(r);
//
//        j.setDescription("Updated Job Description");
//        jobDAO.updateJob(j);
//
//        // DELETE
//        jobDAO.deleteJob(j.getJobId());
//        recruiterDAO.deleteRecruiter(r.getRecruiterId());
//        skillDAO.deleteSkill(skill.getSkillId());
    }
}

