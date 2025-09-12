package com.jobportal.JobPortal.Recruiter.Job;
import com.jobportal.JobPortal.Recruiter.Recruiters.Recruiter;
import com.jobportal.JobPortal.Recruiter.Skill.Skill;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String title;
    private String description;
    private String jobLocation;
    private String salaryPackage;
    private LocalDate postedDate;
    private LocalDate expiryDate;


    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;


    @ManyToMany
    @JoinTable(
            name = "job_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skill = new HashSet<>();

    public Job() {}

    @PrePersist
    public void prePersist() {
        this.postedDate = LocalDate.now();
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(String salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiterUser(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
    public Set<Skill> getSkill() {
        return skill;
    }

    public void setSkill(Set<Skill> skill) {
        this.skill = skill;
    }

//    @Override
//    public String toString() {
//        return "Job{" +
//                "jobId=" + jobId +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", jobLocation='" + jobLocation + '\'' +
//                ", salaryPackage='" + salaryPackage + '\'' +
//                ", postedDate=" + postedDate +
//                ", expiryDate=" + expiryDate +
//                ", skill=" + skill +
//                '}';
//    }
}
