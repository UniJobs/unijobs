package core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    public UniUser toUser(){
        UniUser user = new UniUser();
        user.setUsername(username);
        user.setPassword(password);
              user.setEmail(email);
        return user;
    }
}
