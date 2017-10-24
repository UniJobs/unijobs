package web.dto;

import core.model.Job;
import core.model.Provider;
import core.model.Skill;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SkillDTO {


    public Integer id;

    public String description;

    public List<Job> jobs;

    public List<Provider> providers;

    public SkillDTO(Skill skill){
        id = skill.getId();
        description = skill.getDescription();
        jobs = skill.getJobs();
        providers = getProviders();
    }

}
