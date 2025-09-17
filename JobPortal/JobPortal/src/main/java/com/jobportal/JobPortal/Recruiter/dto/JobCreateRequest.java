package com.jobportal.JobPortal.Recruiter.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class JobCreateRequest {
    private String title;
    private String description;
    private String jobLocation;
    private String salaryPackage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;
    private Long recruiterId;          // recruiter foreign key

    //private List<String> skillNames;
//    public List<String> getSkillNames() {
//        return skillNames;
//    }

//    public void setSkillNames(List<String> skillNames) {
//        this.skillNames = skillNames;
//    }

     private Set<Long> skillId;        // list of skill IDs to attach



    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
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

//    public Set<Long> getSkillId() {
//        return skillId;
//    }
//    public void setSkillId(Set<Long> skillId) {
//        this.skillId = skillId;
//    }

}
