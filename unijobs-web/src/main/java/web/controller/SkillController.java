package web.controller;


import core.model.Skill;
import core.service.FetchService;
import core.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import web.dto.SkillDTO;
import web.dtos.SkillsDTO;


@RestController
public class SkillController {
    private static final Logger log = (Logger) LoggerFactory.getLogger(SkillController.class);

    @Autowired
    FetchService fetchService;

    @Autowired
    ManageService manageService;

    @RequestMapping(value="skills", method = RequestMethod.GET)
    public SkillsDTO getSkills(){
        return new SkillsDTO(fetchService.getAllSkills());
    }

    @RequestMapping(value="/newskill", method = RequestMethod.POST)
    public SkillDTO addUser(@RequestBody final SkillDTO skillDTO){
        log.trace("addSkill: skillDTO = {}", skillDTO);

        Skill skill;
        try{
            skill = Skill.builder()
                    .description(skillDTO.getDescription())
                    .jobs(skillDTO.getJobs())
                    .providers(skillDTO.getProviders())
                    .build();
            manageService.addSkill(skill);
        } catch (DataIntegrityViolationException e) {
            skill = Skill.builder().build();
            log.trace("not added skill = {}", skill);
        }
        return new SkillDTO(skill);
    }

    @RequestMapping(value = "/skill/{skillId}", method = RequestMethod.PUT)
    public SkillDTO updateSkill(
            @PathVariable final Long skillId,
            @RequestBody final SkillDTO skillDTO){
        log.trace("updateSkill: skillId = {}, skillDTO = {}", skillId, skillDTO);

        Skill skill;
        try{
            skill = Skill.builder()
                    .description(skillDTO.getDescription())
                    .jobs(skillDTO.getJobs())
                    .providers(skillDTO.getProviders())
                    .build();
            manageService.updateSkill(skillId, skill);
        } catch (DataIntegrityViolationException e) {
            skill = Skill.builder().build();
            log.trace("not updated skil = {}", skill);
        }
        return new SkillDTO(skill);
    }
}
