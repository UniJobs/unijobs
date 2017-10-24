package web.dtos;

import core.model.Skill;
import lombok.*;
import web.dto.SkillDTO;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SkillsDTO {

    List<SkillDTO> skills;

    public SkillsDTO(List<Skill> skills){
        this.skills = skills.stream().map(SkillDTO::new).collect(Collectors.toList());
    }

    public void addSkill(Skill skill){
        skills.add(new SkillDTO(skill));
    }
}
