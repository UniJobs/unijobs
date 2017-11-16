package core.model;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 10/22/2017.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private static final Logger log = LoggerFactory.getLogger(Job.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private int hoursPerWeek;

    @Column
    private int cost;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UniUser uniUser;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "job_skill",
            joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skills = new ArrayList<>();

    public void addSkill(Skill skill){ this.skills.add(skill); }

    public Job(String description ,String location, int hoursPerWeek, int cost, Date startDate, Date endDate) {
        this.description = description;
        this.location = location;
        this.hoursPerWeek = hoursPerWeek;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
