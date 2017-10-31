package core.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 10/22/2017.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "skill_job",
            joinColumns = {@JoinColumn(name = "skill_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")})
    private List<Job> jobs = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "provider_skill",
        joinColumns = {@JoinColumn(name = "skill_id")},
        inverseJoinColumns = {@JoinColumn(name = "provider_id")})
    private List<Provider> providers = new ArrayList<>();

    public Skill(String description){
        this.description = description;
    }

}
