package com.jobportal.JobPortal.Recruiter.dto;

import java.time.LocalDate;
import java.util.Set;

public class JobUpdateRequest {
    private String title;
    private String description;
    private String jobLocation;
    private String salaryPackage;
    private LocalDate expiryDate;
    private Set<Long> skillId;   // if recruiter want to update  skill

    // Constructors
    public JobUpdateRequest() {}

    public JobUpdateRequest(String title, String description, String jobLocation,
                            String salaryPackage, LocalDate expiryDate, Set<Long> skillId) {
        this.title = title;
        this.description = description;
        this.jobLocation = jobLocation;
        this.salaryPackage = salaryPackage;
        this.expiryDate = expiryDate;
        this.skillId = skillId;
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

    public String getJobLocation() {
        return jobLocation;
    }
    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getSalaryPackage() {
        return salaryPackage;
    }
    public void setSalaryPackage(String salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Set<Long> getSkillId() {
        return skillId;
    }
    public void setSkillId(Set<Long> skillId) {
        this.skillId = skillId;
    }
}
