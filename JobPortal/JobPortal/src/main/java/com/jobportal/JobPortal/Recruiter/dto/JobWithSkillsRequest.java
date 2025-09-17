package com.jobportal.JobPortal.Recruiter.dto;

import java.util.List;

public class JobWithSkillsRequest {
    private JobCreateRequest jobRequest;
    private List<SkillCreateRequest> skills;

    public JobCreateRequest getJobRequest() {
        return jobRequest;
    }

    public void setJobRequest(JobCreateRequest jobRequest) {
        this.jobRequest = jobRequest;
    }

    public List<SkillCreateRequest> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillCreateRequest> skills) {
        this.skills = skills;
    }

}
