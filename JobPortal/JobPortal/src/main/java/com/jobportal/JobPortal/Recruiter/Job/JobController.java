package com.jobportal.JobPortal.Recruiter.Job;

import com.jobportal.JobPortal.Recruiter.dto.JobCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired private JobService jobService;

    //Create Job
    @PostMapping
    public Job createJob(@RequestBody JobCreateRequest request){
        return JobService.createJob(request);
    }

    //Read All
    @GetMapping
    public List<Job> listJobs(){
        return jobService.getAllJobs();
    }

    //Read One
    @GetMapping("/{jobId}")
    public Job getJob(@PathVariable Long id) {
        return jobService.getJob(id);
    }

    //update
    @PutMapping("/{jobId}")
    public Job updateJob(@PathVariable Long id, @RequestBody JobCreateRequest request) {
        return jobService.updateJob(id, request);
    }

    //Delete
    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable Long id) {
        jobService.delteJob(id);
    }

}
