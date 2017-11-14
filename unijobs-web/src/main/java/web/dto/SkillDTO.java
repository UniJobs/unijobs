package web.dto;

import core.model.Skill;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SkillDTO {


    public Integer id;

    public String description;

    public List<Integer> jobIds;

    public List<Integer> uniUserIds;

    public SkillDTO(Skill skill){
        id = skill.getId();
        description = skill.getDescription();
        jobIds = skill.getJobs().stream().map(j -> j.getId()).collect(Collectors.toList());
        uniUserIds = skill.getUniUsers().stream().map(u -> u.getId()).collect(Collectors.toList());
    }

}
