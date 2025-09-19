package com.jobportal.JobPortal.Recruiter.Job;
import com.jobportal.JobPortal.Recruiter.Recruiters.Recruiter;
import com.jobportal.JobPortal.Recruiter.Recruiters.RecruiterRepository;
import com.jobportal.JobPortal.Recruiter.Skill.Skill;
import com.jobportal.JobPortal.Recruiter.Skill.SkillRepository;
import com.jobportal.JobPortal.Recruiter.dto.JobCreateRequest;
import com.jobportal.JobPortal.Recruiter.dto.JobUpdateRequest;
import com.jobportal.JobPortal.Recruiter.dto.JobWithSkillsResponse;
import com.jobportal.JobPortal.Recruiter.dto.JobWithSkillsUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JobService {
    @Autowired private JobRepository jobRepository;
    @Autowired private RecruiterRepository recruiterRepository;
    @Autowired private SkillRepository skillRepository;

    //creating job with skills
    public Job createJob(Long recruiterId, JobCreateRequest request, Set<Skill> skills) {

        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        // Make sure all skills have recruiter set
        Set<Skill> skillSet = new HashSet<>();
        for (Skill s : skills) {
            if (s.getRecruiter() == null) {
                s.setRecruiter(recruiter);
                s = skillRepository.save(s);
            }
            skillSet.add(s);
        }

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setPostedDate(LocalDate.now());
        job.setExpiryDate(request.getExpiryDate());
        job.setSalaryPackage(request.getSalaryPackage());
        job.setJobLocation(request.getJobLocation());
        job.setRecruiter(recruiter); // must set recruiter
        job.setSkill(skillSet);

        return jobRepository.save(job);
    }

    //To see all job and skill one time which is uploaded by recruiter
    public List<JobWithSkillsResponse> getRecruiterJobsWithSkills(Long recruiterId) {
        List<Job> jobs = jobRepository.findByRecruiter_RecruiterId(recruiterId);

        // Final response list
        List<JobWithSkillsResponse> responseList = new ArrayList<>();

        for (Job job : jobs) {
            JobWithSkillsResponse dto = new JobWithSkillsResponse();
            dto.setJobId(job.getJobId());
            dto.setTitle(job.getTitle());
            dto.setDescription(job.getDescription());
            dto.setJobLocation(job.getJobLocation());
            dto.setSalaryPackage(job.getSalaryPackage());
            dto.setPostedDate(job.getPostedDate());
            dto.setExpiryDate(job.getExpiryDate());

            // Recruiter info also
            if (job.getRecruiter() != null) {
                dto.setRecruiterName(job.getRecruiter().getName());
            }
            // Skills ka set banate hain
            Set<String> skillNames = new HashSet<>();
            for (Skill skill : job.getSkill()) {
                skillNames.add(skill.getSkillName());
            }
            dto.setSkills(skillNames);

            // dto ko list me add kar diya
            responseList.add(dto);
        }

        return responseList;
    }

    public List<JobWithSkillsResponse> getAllJobsWithSkills() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithSkillsResponse> responseList = new ArrayList<>();

        for (Job job : jobs) {
            JobWithSkillsResponse dto = new JobWithSkillsResponse();
            dto.setJobId(job.getJobId());
            dto.setTitle(job.getTitle());
            dto.setDescription(job.getDescription());
            dto.setJobLocation(job.getJobLocation());
            dto.setSalaryPackage(job.getSalaryPackage());
            dto.setPostedDate(job.getPostedDate());
            dto.setExpiryDate(job.getExpiryDate());

            // Recruiter info also
            if (job.getRecruiter() != null) {
               // dto.setRecruiterId(job.getRecruiter().getRecruiterId());
                dto.setRecruiterName(job.getRecruiter().getName());
            }

            // Skills add karo
            Set<String> skillNames = new HashSet<>();
            if (job.getSkill() != null) {
                for (Skill skill : job.getSkill()) {
                    skillNames.add(skill.getSkillName());
                }
            }
            dto.setSkills(skillNames);

            responseList.add(dto);
        }

        return responseList;
    }

//    //get all job with skills
//    public List<Job> getAllJobsWithSkills() {
//        return jobRepository.findAll();
//    }

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

            if (!job.getRecruiter().getRecruiterId().equals(recruiterId)) {
            throw new RuntimeException("Not authorized");
             }

            job.setTitle(req.getTitle());
            job.setDescription(req.getDescription());
            job.setSalaryPackage(req.getSalaryPackage());
            job.setJobLocation(req.getJobLocation());
            job.setExpiryDate(req.getExpiryDate());
            return jobRepository.save(job);
        }

    public Job updateJobWithSkills(Long jobId, JobWithSkillsUpdateRequest req) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // recruiter authorization check
        if (!job.getRecruiter().getRecruiterId().equals(req.getRecruiterId())) {
            throw new RuntimeException("Not authorized");
        }

        // update job details
        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setJobLocation(req.getJobLocation());
        job.setSalaryPackage(req.getSalaryPackage());
        job.setExpiryDate(req.getExpiryDate());

        // overwrite skills
        Set<Skill> newSkills = new HashSet<>();
        Recruiter recruiter = job.getRecruiter();

        for (String skillName : req.getSkills()) {
            Skill skill = skillRepository.findBySkillNameAndRecruiter(skillName, recruiter)
                    .orElseGet(() -> {
                        Skill newSkill = new Skill();
                        newSkill.setSkillName(skillName);
                        newSkill.setRecruiter(recruiter);
                        return skillRepository.save(newSkill);
                    });
            newSkills.add(skill);
        }
        job.setSkill(newSkills);

        return jobRepository.save(job);
    }


    public void deleteJob(Long recruiterId, Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getRecruiter().getRecruiterId().equals(recruiterId)) {
            throw new RuntimeException("Not authorized");
        }

        // Clear relation (safety)
        job.getSkill().clear();

        // Delete job (skills will also be deleted because of cascade + orphanRemoval)
        jobRepository.delete(job);
    }

}
