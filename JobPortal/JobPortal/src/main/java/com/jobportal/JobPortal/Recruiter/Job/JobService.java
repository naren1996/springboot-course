package com.jobportal.JobPortal.Recruiter.Job;
import com.jobportal.JobPortal.Recruiter.Recruiters.Recruiter;
import com.jobportal.JobPortal.Recruiter.Recruiters.RecruiterRepository;
import com.jobportal.JobPortal.Recruiter.Skill.Skill;
import com.jobportal.JobPortal.Recruiter.Skill.SkillRepository;
import com.jobportal.JobPortal.Recruiter.dto.JobCreateRequest;
import com.jobportal.JobPortal.Recruiter.dto.JobUpdateRequest;
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

    public Job createJob(Long recruiterId, JobCreateRequest request) {

        // 1. Ensure recruiter exists
        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        // 2. Fetch skills from IDs
        Set<Skill> skill = new HashSet<>(skillRepository.findAllById(request.getSkillId()));
        if (skill.isEmpty()) {
            throw new RuntimeException("No valid skills found for given IDs");
        }

        //3.Build Job
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setPostedDate(LocalDate.now());
        job.setExpiryDate(request.getExpiryDate());
        job.setSalaryPackage(request.getSalaryPackage());
        job.setJobLocation(request.getJobLocation());
        job.setRecruiter(recruiter);
        job.setSkill(skill);

        return jobRepository.save(job);
    }
        //Get all jobs
        public List<Job> listJobsByRecruiter(Long recruiterId) {
            return jobRepository.findByRecruiter_RecruiterId(recruiterId);
        }


        public Job getJob(Long jobId) {
            return jobRepository.findById(jobId)
                    .orElseThrow(() -> new RuntimeException("Job not found"));
        }


         public Job updateJob(Long recruiterId, Long jobId, JobUpdateRequest req) {
            Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

            // recruiter check kare -> सिर्फ़ वही recruiter modify कर सके
            if (!job.getRecruiter().getRecruiterId().equals(recruiterId)) {
            throw new RuntimeException("Not authorized");
             }

            job.setTitle(req.getTitle());
            job.setDescription(req.getDescription());
            job.setSalaryPackage(req.getSalaryPackage());
            job.setJobLocation(req.getJobLocation());
            job.setExpiryDate(req.getExpiryDate());
            // skills भी अपडेट करने हों तो skillRepo.findAllById(...)
            return jobRepository.save(job);
        }


        public void deleteJob(Long recruiterId, Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getRecruiter().getRecruiterId().equals(recruiterId)) {
            throw new RuntimeException("Not authorized");
        }

        jobRepository.delete(job);
    }


}
