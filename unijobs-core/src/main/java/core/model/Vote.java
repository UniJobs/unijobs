package core.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "votes")
public class Vote {
    @Id
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String voter;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String vote;
}