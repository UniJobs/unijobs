package core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Alex on 10/22/2017.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recommandation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Client fromClient;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Client toClient;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

}
