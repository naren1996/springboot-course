package com.jobportal.JobPortal.Recruiter.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    //Optional<Skill> findById(Long skillId);
    boolean existsBySkillName(String skillName);

    List<Skill> findByRecruiterRecruiterId(Long recruiterId);
}
