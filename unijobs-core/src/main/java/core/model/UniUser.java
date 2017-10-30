package core.model;

import lombok.*;

import javax.enterprise.inject.Default;
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

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviewerReviews = new ArrayList<>();

    @OneToMany(mappedBy = "reviewed")
    private List<Review> reviewedReviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    //TODO: picture representation ???
    //@Column
    //private String picture;

}
