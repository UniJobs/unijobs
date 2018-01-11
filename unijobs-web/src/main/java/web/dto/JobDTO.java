package web.dto;

import core.model.Job;
import lombok.*;

import java.util.Date;
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

    public String title;

    public String description;

    public Integer hpw;

    public Integer cost;

    public Integer uniUserId;

    public String location;

    public String startDate;

    public String endDate;

    public List<Integer> skillIds;

    public JobDTO(Job job){
        id = job.getId();
        title = job.getTitle();
        description = job.getDescription();
        uniUserId = job.getUniUser().getId();
        skillIds = job.getSkills().stream().map(s -> s.getId()).collect(Collectors.toList());
        cost = job.getCost();
        hpw = job.getHoursPerWeek();
        location = job.getLocation();
        if(job.getStartDate() != null)
            startDate = job.getStartDate().toString();
        if(job.getEndDate() != null)
            endDate = job.getEndDate().toString();
    }
}
