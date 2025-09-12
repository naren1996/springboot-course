package com.jobportal.JobPortal.Recruiter.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // ➤ Create a new skill
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // ➤ Read all skills
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    //skillRepository.findById(id).get().getJobs().size();
    // ➤ Read single skill by ID
    public Skill getSkillById(Long skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + skillId));
    }

    // ➤ Update skill name
    public Skill updateSkill(Long id, Skill updatedSkill) {
        Skill skill = getSkillById(id);
        skill.setSkillName(updatedSkill.getSkillName());
        return skillRepository.save(skill);
    }

    // ➤ Delete skill
    public void deleteSkill(Long skillId) {
        skillRepository.deleteById(skillId);
    }
}

