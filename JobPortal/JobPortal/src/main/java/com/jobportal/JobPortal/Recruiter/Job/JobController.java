package com.jobportal.JobPortal.Recruiter.Job;

import com.jobportal.JobPortal.Recruiter.dto.JobCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;    // instance inject

    @PostMapping
    public Job createJob(@RequestBody JobCreateRequest request) {
        return jobService.createJob(request);   // object ke through
    }

    //Read All
    @GetMapping
    public List<Job> listJobs(){
        return jobService.getAllJobs();
    }

    //Read One
    @GetMapping("/{jobId}")
    public Job getJob(@PathVariable Long jobId) {
        return jobService.getJob(jobId);
    }

    //update
    @PutMapping("/{jobId}")
    public Job updateJob(@PathVariable Long jobId, @RequestBody JobCreateRequest request) {
        return jobService.updateJob(jobId, request);
    }

    //Delete
    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
    }
}
