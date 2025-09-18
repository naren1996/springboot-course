package com.jobportal.JobPortal.Recruiter.Job;
import com.jobportal.JobPortal.Recruiter.Skill.Skill;
import com.jobportal.JobPortal.Recruiter.Skill.SkillService;
import com.jobportal.JobPortal.Recruiter.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recruiter/job")
//@RequestMapping("/recruiter/job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private SkillService skillService;// instance inject

//    @PostMapping("/post_job")
//    public Job createJob(@RequestBody JobCreateRequest request) {
//        return jobService.createJob(request);   // object ke through
//    }

    //When Skills are already saved in DB
//    @PostMapping("/post_job/{recruiterId}")
//    public Job createJob(@PathVariable Long recruiterId,
//                         @RequestBody JobCreateRequest request) {
//        return jobService.createJob(recruiterId, request);
//
//    }

    //When each recruiter creates their own skill during job post
    @PostMapping("/post_job/{recruiterId}")
    public Job createJobWithSkills(@PathVariable Long recruiterId,
                                   @RequestBody JobWithSkillsRequest request) {

        // Create or fetch skills for this recruiter
        Set<Skill> skills = skillService.createOrFetchSkills(recruiterId, request.getSkills());

        // Pass skills to service
        return jobService.createJob(recruiterId, request.getJobRequest(), skills);
    }

    @GetMapping("/jobs-with-skills/{recruiterId}")
    public List<JobWithSkillsResponse> getRecruiterJobsWithSkills(@PathVariable Long recruiterId) {
        return jobService.getRecruiterJobsWithSkills(recruiterId);
    }


    @GetMapping("/viewAllJobs")
    public List<JobWithSkillsResponse> getAllJobs() {
        return jobService.getAllJobsWithSkills();
    }

    @GetMapping("/viewAllJob")
    public List<Job> listJobsByRecruiter(@PathVariable Long recruiterId) {
        return jobService.listJobsByRecruiter(recruiterId);
    }

    //Read One
    @GetMapping("/jobsearch/{jobId}")
    public Job getJob(@PathVariable Long jobId) {
        return jobService.getJob(jobId);
    }

    //update
    @PutMapping("/jobUpdate/{jobId}")
    public Job updateJob(@PathVariable Long recruiterId,
                         @PathVariable Long jobId,
                         @RequestBody JobUpdateRequest request) {
        return jobService.updateJob(recruiterId, jobId, request);
    }


    @DeleteMapping("/deleteJob/{jobId}")
    public void deleteJob(@PathVariable Long recruiterId,
                          @PathVariable Long jobId) {
        jobService.deleteJob(recruiterId, jobId);
    }
}
