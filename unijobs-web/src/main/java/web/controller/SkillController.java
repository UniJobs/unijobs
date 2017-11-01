package web.controller;

import core.model.Skill;
import core.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.dto.SkillDTO;
import web.dtos.SkillsDTO;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
    private static final Logger log = (Logger) LoggerFactory.getLogger(SkillController.class);

    @Autowired
    SkillService skillService;

    @Transactional
    @RequestMapping(value="skills", method = RequestMethod.GET)
    public SkillsDTO getSkills(){
        return new SkillsDTO(skillService.getAll());
    }

    @RequestMapping(value="/newskill", method = RequestMethod.POST)
    public SkillDTO addSkill(@RequestBody final SkillDTO skillDTO){
        log.trace("addSkill: skillDTO = {}", skillDTO);

        Skill skill;
        try{
            skill = Skill.builder()
                    .description(skillDTO.getDescription())
                    .jobs(new ArrayList<>())
                    .providers(new ArrayList<>())
                    .build();
            skillService.insert(skill);
        } catch (DataIntegrityViolationException e) {
            skill = Skill.builder().build();
            log.trace("not added skill = {}", skill);
        }
        return new SkillDTO(skill);
    }

    //TODO: a skill will be updated only when a provider picks it from a list of skills
    //TODO: or when a new job requiring that skill is added
//    @RequestMapping(value = "/skill/{skillId}", method = RequestMethod.PUT)
//    public SkillDTO updateSkill(
//            @PathVariable final int skillId,
//            @RequestBody final SkillDTO skillDTO){
//        log.trace("updateSkill: skillId = {}, skillDTO = {}", skillId, skillDTO);
//
//        Skill skill;
//        try{
//            skill = Skill.builder()
//                    .description(skillDTO.getDescription())
//                    .jobs(skillDTO.getJobs())
//                    .providers(skillDTO.getProviders())
//                    .build();
//            skillService.update(skillId, skill);
//        } catch (DataIntegrityViolationException e) {
//            skill = Skill.builder().build();
//            log.trace("not updated skil = {}", skill);
//        }
//        return new SkillDTO(skill);
//    }

}
