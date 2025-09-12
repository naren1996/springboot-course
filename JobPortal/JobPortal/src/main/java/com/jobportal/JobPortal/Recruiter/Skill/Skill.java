package com.jobportal.JobPortal.Recruiter.Skill;

import com.jobportal.JobPortal.Recruiter.Job.Job;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;
    private String skillName;

    @ManyToMany(mappedBy = "skill", fetch = FetchType.EAGER)
    private Set<Job> job = new HashSet<>();

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setJob(Set<Job> job) {
        this.job = job;
    }

    public Set<Job> getJob() {
        return job;
    }
}
