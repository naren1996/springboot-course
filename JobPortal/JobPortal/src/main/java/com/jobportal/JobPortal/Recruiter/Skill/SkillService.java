package com.jobportal.JobPortal.Recruiter.Skill;
import com.jobportal.JobPortal.Recruiter.Recruiters.Recruiter;
import com.jobportal.JobPortal.Recruiter.Recruiters.RecruiterRepository;
import com.jobportal.JobPortal.Recruiter.dto.SkillCreateRequest;
import com.jobportal.JobPortal.Recruiter.dto.SkillUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private RecruiterRepository recruiterRepository;

    public SkillService(SkillRepository skillRepository, RecruiterRepository recruiterRepository) {
        this.skillRepository = skillRepository;
        this.recruiterRepository = recruiterRepository;
    }


    // Create a new skill
   // public Skill createSkill(Skill skill) {
//        return skillRepository.save(skill);
//    }


    // Create Skill
//    public Skill createSkill(SkillCreateRequest request) {
//        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
//                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
//
//        Skill skill = new Skill();
//        skill.setSkillName(request.getSkillName());
//        skill.setRecruiter(recruiter);
//        return skillRepository.save(skill);
//    }

    public Set<Skill> createOrFetchSkills(Long recruiterId, List<SkillCreateRequest> skillRequests) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found!"));

        Set<Skill> skillSet = new HashSet<>();

        for (SkillCreateRequest req : skillRequests) {
            Skill skill = skillRepository.findBySkillNameAndRecruiter(req.getSkillName(), recruiter)
                    .orElseGet(() -> {
                        Skill newSkill = new Skill();
                        newSkill.setSkillName(req.getSkillName());
                        newSkill.setRecruiter(recruiter);
                        return skillRepository.save(newSkill);
                    });
            skillSet.add(skill);
        }
        return skillSet;

    }



    // Get All Skills by Recruiter
    public List<Skill> getSkillsByRecruiter(Long recruiterId) {
        return skillRepository.findByRecruiterRecruiterId(recruiterId);
    }
    // Read all skills
//    public List<Skill> getAllSkills(Long recruiterId) {
//        return skillRepository.findAll(recruiterId);
//    }



//    // Get All Skills by Recruiter
//    public List<Skill> getSkillsByRecruiter(Long recruiterId) {
//        return skillRepository.findByRecruiterRecruiterId(recruiterId);
//    }
    
    
    // Read single skill by ID
    public Skill getSkillById(Long skillId) {
        return skillRepository.findById(skillId).orElseThrow(() -> new RuntimeException("Skill not found with id: " + skillId));
    }


    // Update skill name
//    public Skill updateSkill(Long skillId, Skill updatedSkill) {
//        Skill skill = getSkillById(skillId);
//        skill.setSkillName(updatedSkill.getSkillName());
//        return skillRepository.save(skill);
//    }


    // Update Skill (only if recruiter owns it)
    public Skill updateSkill(Long skillId, SkillUpdateRequest request) {
        // 1. Skill fetch
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        // 2. Ownership check
        if (!skill.getRecruiter().getRecruiterId().equals(request.getRecruiterId())) {
            throw new RuntimeException("You are not authorized to update this skill");
        }

        // 3. Update fields
        skill.setSkillName(request.getSkillName());

        return skillRepository.save(skill);
    }


    // Delete skill
        //public void deleteSkill(Long skillId) {
       // skillRepository.deleteById(skillId);
    //}

    // Delete Skill (only if recruiter owns it)
    public void deleteSkill(Long skillId, Long recruiterId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        if (!skill.getRecruiter().getRecruiterId().equals(recruiterId)) {
            throw new RuntimeException("You are not authorized to delete this skill");
        }

        skillRepository.delete(skill);
    }


}

