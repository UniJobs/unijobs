package core.model;

import com.sun.org.apache.regexp.internal.RE;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 10/22/2017.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UniUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Boolean enabled = true;

    @Column
    private String email;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private Date dob;

    @Column
    private String phone;

    @OneToMany(mappedBy = "reviewer", fetch = FetchType.LAZY)
    private List<Review> reviewerReviews = new ArrayList<>();

    @OneToMany(mappedBy = "reviewed", fetch = FetchType.LAZY)
    private List<Review> reviewedReviews = new ArrayList<>();

    @OneToMany(mappedBy = "uniUser", fetch = FetchType.LAZY)
    private List<Job> myJobs = new ArrayList<>();

    @OneToMany(mappedBy = "fromUniUser", fetch = FetchType.LAZY)
    private List<Request> fromRequests = new ArrayList<>();

    @OneToMany(mappedBy = "toUniUser", fetch = FetchType.LAZY)
    private List<Request> toRequests = new ArrayList<>();

    @OneToMany(mappedBy = "fromUniUser", fetch = FetchType.LAZY)
    private List<Recommendation> sent = new ArrayList<>();

    @OneToMany(mappedBy = "toUniUser", fetch = FetchType.LAZY)
    private List<Recommendation> received = new ArrayList<>();

    @OneToMany(mappedBy = "forUniUser", fetch = FetchType.LAZY)
    private List<Recommendation> mentioned = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_skill",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skills = new ArrayList<>();

    public UniUser(String username, String password, String email, String firstname, String lastname, Date dob, String phone){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.phone = phone;
        this.skills = new ArrayList<>();
    }

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    //TODO: picture representation ???
    //@Column
    //private String picture;

}
