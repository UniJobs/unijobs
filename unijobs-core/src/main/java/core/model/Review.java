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
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private UniUser reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewed_id", nullable = false)
    private UniUser reviewed;

    @Column
    private int stars;

    @Column
    private String comment;
}
