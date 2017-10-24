package web.controller;


import core.service.FetchService;
import core.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dtos.SkillsDTO;

@RestController
public class SkillController {

    @Autowired
    FetchService fetchService;

    @Autowired
    ManageService manageService;

    @RequestMapping(value="skills", method = RequestMethod.GET)
    public SkillsDTO getSkills(){
        return new SkillsDTO(fetchService.getAllSkill());
    }
}
