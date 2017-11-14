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
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private UniUser fromUniUser;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private UniUser toUniUser;

    @ManyToOne
    @JoinColumn(name = "for_id")
    private UniUser forUniUser;

}
