package web.controller;

import core.model.UniUser;
import core.model.Vote;
import core.service.UniUserService;
import core.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dto.VoteDTO;
import web.security.RSAController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    private static final Logger log = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    UniUserService uniUserService;

    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/newVote", method = RequestMethod.POST)
    public VoteDTO addVote(
            Authentication authentication, @RequestBody final VoteDTO voteDTO) {

        log.trace("addVote: voteDto={}", voteDTO);
        Vote vote;
        try {
            vote = Vote.builder()
                    .voter(Arrays.toString((RSAController.encrypt(authentication.getName().getBytes(StandardCharsets.UTF_8)))))
                    .vote(Arrays.toString((RSAController.encrypt(voteDTO.getVote().getBytes(StandardCharsets.UTF_8)))))
                    .build();
            log.trace("addVote: vote encrypted={}", vote);
            UniUser user = uniUserService.getUserByUsername(authentication.getName());
            if(!user.getPhone().equalsIgnoreCase("voted")) {
                user.setPhone("voted");
                uniUserService.updateUser(user);
                voteService.addVote(vote);
            } else {
                return new VoteDTO(authentication.getName(), "User already voted!");
            }
        } catch (DataIntegrityViolationException e) {
            vote = Vote.builder().build();
            log.trace("not added vote={}", vote);
        }
        return new VoteDTO(vote);
    }
}
