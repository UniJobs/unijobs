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

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private List<Job> jobs = new ArrayList<>();

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private List<UniUser> uniUsers = new ArrayList<>();

    public Skill(String description){
        this.description = description;
    }
}
