package web.dto;

import core.model.Client;
import core.model.Job;
import core.model.Skill;
import lombok.*;

import java.util.List;

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

    public String descriiption;

    public Client client;

    public List<Skill> skills;

    public JobDTO(Job job){
        id = job.getId();
        descriiption = job.getDescription();
        client = job.getClient();
        skills = job.getSkills();
    }
}
