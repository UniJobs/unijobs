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

//TODO: Get rid of duplicate code
//TODO: Instead of Having .stream().filter()..etc repeated in the Controllers
//TODO: This Constructor could receive a List<Job> and do the conversion internally
//TODO: PS: writing this probably took longer than doing it myself

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
