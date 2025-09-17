package com.jobportal.JobPortal.Recruiter.Skill;
import com.jobportal.JobPortal.Recruiter.dto.SkillCreateRequest;
import com.jobportal.JobPortal.Recruiter.dto.SkillUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@RequestMapping("/recruiter/{recruiterId}/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

//    @PostMapping
//    public Skill createSkill(@RequestBody Skill skill) {
//        return skillService.createSkill(skill);
//    }

    // Create
//    @PostMapping("/createSkill")
//    public Skill createSkill(@PathVariable Long recruiterId, @RequestBody SkillCreateRequest request) {
//        request.setRecruiterId(recruiterId);
//        return skillService.createSkill(request);
//    }

//    @GetMapping
//    public List<Skill> getAllSkills() {
//        return skillService.getAllSkills();
//    }

    // Get All Skills of Recruiter
    @GetMapping("/all_skills")
    public List<Skill> getSkills(@PathVariable Long recruiterId) {
        return skillService.getSkillsByRecruiter(recruiterId);

    }
//    @GetMapping("/{skillId}")
//    public Skill getSkill(@PathVariable Long skillId) {
//        return skillService.getSkillById(skillId);
//    }

//    @PutMapping("/{skillId}")
//    public Skill updateSkill(@PathVariable Long skillId, @RequestBody Skill updatedSkill) {
//        return skillService.updateSkill(skillId, updatedSkill);
//    }


//    @DeleteMapping("/{skillId}")
//    public void deleteSkill(@PathVariable Long skillId) {
//        skillService.deleteSkill(skillId);
//    }

        // Update
//    @PutMapping("/update/{skillId}")
//    public Skill updateSkill(@PathVariable Long recruiterId, @PathVariable Long skillId,
//                                             @RequestBody SkillUpdateRequest request) {
//        request.setRecruiterId(recruiterId);
//        return skillService.updateSkill(, )
//    }

        // Update
        @PutMapping("/update/{skillId}")
        public Skill updateSkill (@PathVariable Long recruiterId,
                @PathVariable Long skillId,
                @RequestBody SkillUpdateRequest request) {
            request.setRecruiterId(recruiterId);
            return skillService.updateSkill(skillId, request);
        }

        // Delete
        @DeleteMapping("/delete/{skillId}")
        public void deleteSkill (@PathVariable Long skillId, @PathVariable Long recruiterId){
            skillService.deleteSkill(skillId, recruiterId);
        }
}
