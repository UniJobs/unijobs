package core.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Ionut on 10/22/2018.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Boolean enabled = true;

}
