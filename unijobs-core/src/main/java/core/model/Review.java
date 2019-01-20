package core.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Ionut on 10/22/2018.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
