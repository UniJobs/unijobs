package web.dto;

import core.model.UniUser;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UniUserDTO {
    public Integer id;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String lastname;

    private Date dob;

    //The reviews lists were not added now after a stand-up with Bogdan and SCRUM Master, Alexandra!!!

    public UniUserDTO(UniUser user){
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        dob = user.getDob();
    }
}
