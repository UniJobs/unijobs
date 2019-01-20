package core.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Ionut on 10/22/2018.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private UniUser fromUniUser;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private UniUser toUniUser;

    // Request STATUS : PENDING | ACCEPTED | REJECTED
    @Column
    private String status;
}
