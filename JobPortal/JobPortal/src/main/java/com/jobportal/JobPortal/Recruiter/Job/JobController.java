package com.jobportal.JobPortal.Recruiter.Job;
import com.jobportal.JobPortal.Recruiter.dto.JobCreateRequest;
import com.jobportal.JobPortal.Recruiter.dto.JobUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter/{recruiterId}/job")
public class JobController {
    @Autowired
    private JobService jobService;    // instance inject

//    @PostMapping("/post_job")
//    public Job createJob(@RequestBody JobCreateRequest request) {
//        return jobService.createJob(request);   // object ke through
//    }

    @PostMapping("/post_job")
    public Job createJob(@PathVariable Long recruiterId,
                         @RequestBody JobCreateRequest request) {
        return jobService.createJob(recruiterId, request);
    }

    //Read All
//    @GetMapping("/viewAllJob")
//    public List<Job> listJobs(){
//        return jobService.getAllJobs();
//    }

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
//    @PutMapping(value = "/jobUpdate/{jobId}")
//    public Job updateJob(@PathVariable Long jobId, @RequestBody JobCreateRequest request) {
//        return jobService.updateJob(jobId, request);
//    }

    //update
    @PutMapping("/jobUpdate/{jobId}")
    public Job updateJob(@PathVariable Long recruiterId,
                         @PathVariable Long jobId,
                         @RequestBody JobUpdateRequest request) {
        return jobService.updateJob(recruiterId, jobId, request);
    }

    //Delete
//    @DeleteMapping("/deleteJob/{jobId}")
//    public void deleteJob(@PathVariable Long jobId) {
//        jobService.deleteJob(jobId);
//    }

    @DeleteMapping("/deleteJob/{jobId}")
    public void deleteJob(@PathVariable Long recruiterId,
                          @PathVariable Long jobId) {
        jobService.deleteJob(recruiterId, jobId);
    }
}
