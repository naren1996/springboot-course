package com.jobportal.JobPortal.Recruiter.Job;

import com.jobportal.JobPortal.Recruiter.Recruiters.Recruiter;
import com.jobportal.JobPortal.Recruiter.Recruiters.RecruiterRepository;
import com.jobportal.JobPortal.Recruiter.Recruiters.RecruiterService;
import com.jobportal.JobPortal.Recruiter.Skill.Skill;
import com.jobportal.JobPortal.Recruiter.Skill.SkillRepository;
import com.jobportal.JobPortal.Recruiter.dto.JobCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobService {
    @Autowired private JobRepository jobRepository;
    @Autowired private RecruiterRepository recruiterRepository;
    @Autowired private SkillRepository skillRepository;

    public Job createJob(JobCreateRequest request) {

        //1. Recruiter must exists
        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        //2.Fetch skills
        Set<Skill> skill = new HashSet<>(skillRepository.findAllById(request.getSkillIds()));

        //3.Build Job
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setPostedDate(LocalDate.now());
        job.setExpiryDate(request.getExpiryDate());
        job.setSalaryPackage(request.getSalaryPackage());
        job.setJobLocation(request.getJobLocation());
        job.setRecruiterUser(recruiter);
        job.setSkill(skill);

        return jobRepository.save(job);
    }
        //Get all jobs
        public List<Job> getAllJobs() {
            return jobRepository.findAll();
        }

        public Job getJob(Long jobId) {
            return jobRepository.findById(jobId)
                    .orElseThrow(() -> new RuntimeException("Job not found"));
        }

        public Job updateJob(Long jobId, JobCreateRequest request) {
            Job job = getJob(jobId);
            job.setTitle(request.getTitle());
            job.setDescription(request.getDescription());
            job.setSkill(new HashSet<>(skillRepository.findAllById(request.getSkillIds())));
            return jobRepository.save(job);
        }

        public void deleteJob(Long jobId) {
            jobRepository.deleteById(jobId);
        }
    }
}
