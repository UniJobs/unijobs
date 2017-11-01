package core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@AllArgsConstructor
@NoArgsConstructor
public class Job {

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
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany(mappedBy = "jobs", fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    public Job(String description ,String location, int hoursPerWeek, int cost, Date startDate, Date endDate){
        this.description = description;
        this.location = location;
        this.hoursPerWeek = hoursPerWeek;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
