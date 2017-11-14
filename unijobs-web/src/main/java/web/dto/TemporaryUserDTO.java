package web.dto;

import core.model.TemporaryUser;

public class TemporaryUserDTO {

    private Integer id;

    private String username;

    private String password;

    private String email;

    public TemporaryUserDTO(TemporaryUser temporaryUser){
        id = temporaryUser.getId();
        username = temporaryUser.getUsername();
        password = temporaryUser.getPassword();
        email = temporaryUser.getPassword();
    }
}
