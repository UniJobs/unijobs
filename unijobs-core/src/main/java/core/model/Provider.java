package core.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 10/22/2017.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Provider extends UniUser {

    @ManyToMany(mappedBy = "providers", fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    public void addRequest(Request request){
        this.requests.add(request);
    }
}
