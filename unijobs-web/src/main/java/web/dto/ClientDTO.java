package web.dto;

import core.model.Client;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 10/30/2017.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {

    private int id;
    private String username;
    private String email;
    private String firsname;
    private String lastname;

    //TODO: add dependencies to DTO

    public ClientDTO(Client c){
        this.id = c.getId();
        this.username = c.getUsername();
        this.email = c.getEmail();
        this.firsname = c.getFirstname();
        this.lastname = c.getLastname();
    }
}
