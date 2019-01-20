package web.dto;

import core.model.Voter;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VoterDTO {
    public String username;
    private String password;

    public VoterDTO(Voter voter) {
        username = voter.getUsername();
        password = voter.getPassword();
    }
}
