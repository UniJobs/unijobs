package web.dto;

import core.model.Client;
import core.model.Job;
import core.model.Skill;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cris on 10/24/2017.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JobDTO {

    public Integer id;

    public String description;

    public Integer hpw;

    public Integer cost;

    public Integer clientId;

    public String location;

    public List<Integer> skillIds;

    public JobDTO(Job job){
        id = job.getId();
        description = job.getDescription();
        clientId = job.getClient().getId();
        skillIds = job.getSkills().stream().map(s -> s.getId()).collect(Collectors.toList());
        cost = job.getCost();
        hpw = job.getHoursPerWeek();
        location = job.getLocation();
    }
}
