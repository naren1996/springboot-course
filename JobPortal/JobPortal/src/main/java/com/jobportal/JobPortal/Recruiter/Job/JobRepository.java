package com.jobportal.JobPortal.Recruiter.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findById(Long jobId);

    List<Job> findByRecruiter_RecruiterId(Long recruiterId);
}
