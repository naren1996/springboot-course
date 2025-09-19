package com.jobportal.JobPortal.Recruiter.Skill;
import com.jobportal.JobPortal.Recruiter.Recruiters.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findBySkillNameAndRecruiter(String skillName, Recruiter recruiter);
    boolean existsBySkillName(String skillName);

    List<Skill> findByRecruiterRecruiterId(Long recruiterId);
}
