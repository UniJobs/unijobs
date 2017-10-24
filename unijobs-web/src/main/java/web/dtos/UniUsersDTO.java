package web.dtos;

import core.model.UniUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import web.dto.UniUserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UniUsersDTO {
    List<UniUserDTO> users;

    public UniUsersDTO(List<UniUser> users){
        this.users = users.stream().map(UniUserDTO::new).collect(Collectors.toList());
    }

    public void addUser(UniUser uniUser){
        users.add(new UniUserDTO(uniUser));
    }
}
