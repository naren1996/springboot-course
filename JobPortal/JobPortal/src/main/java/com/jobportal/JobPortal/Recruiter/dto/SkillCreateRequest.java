package com.jobportal.JobPortal.Recruiter.dto;

public class SkillCreateRequest {
    private String skillName;
    private Long recruiterId;

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}

