package core.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
public class UniUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

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


    //TODO: picture representation ???
    //@Column
    //private String picture;

}
