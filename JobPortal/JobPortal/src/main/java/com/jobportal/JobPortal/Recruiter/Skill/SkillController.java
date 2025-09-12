package com.jobportal.JobPortal.Recruiter.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{skillId}")
    public Skill getSkill(@PathVariable Long skillId) {
        return skillService.getSkillById(skillId);
    }

    @PutMapping("/{skillId}")
    public Skill updateSkill(@PathVariable Long skillId,
                             @RequestBody Skill updatedSkill) {
        return skillService.updateSkill(skillId, updatedSkill);
    }

    @DeleteMapping("/{skillId}")
    public void deleteSkill(@PathVariable Long skillId) {
        skillService.deleteSkill(skillId);
    }
}
