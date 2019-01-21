package web.dto;

import core.model.Vote;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class VoteDTO {
    public String voter;
    private String vote;

    public VoteDTO(Vote vote) {
        voter = vote.getVoter();
        this.vote = vote.getVote();
    }
}