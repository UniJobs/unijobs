package web.dtos;

import core.model.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import web.dto.JobDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cris on 10/24/2017.
 */

@Setter
@Getter
@NoArgsConstructor
@ToString
public class JobsDTO {
    List<JobDTO> jobs = new ArrayList<>();

    public JobsDTO(List<JobDTO> jobDTOS){
        this.jobs = jobDTOS;
    }

    public void addJob(Job job){jobs.add(new JobDTO(job));}
}
