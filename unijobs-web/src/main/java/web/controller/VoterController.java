package web.controller;

import core.model.Voter;
import core.service.AuthorityService;
import core.service.UniUserService;
import core.service.VoterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dto.VoterDTO;

@RestController
@RequestMapping("/api/voter")
public class VoterController {
    private static final Logger log = LoggerFactory.getLogger(VoterController.class);

    @Autowired
    VoterService voterService;

    @Autowired
    AuthorityService authorityService;

    @RequestMapping(value = "/newVoter", method = RequestMethod.POST)
    public VoterDTO addVoter(
            @RequestBody final VoterDTO voterDTO){

        log.trace("addUser: voterDTO={}", voterDTO);
        Voter voter;
        //user = uniUserService.getUserByUsername(userDTO.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            voter = Voter.builder().username(voterDTO.getUsername()).password(encoder.encode(voterDTO.getPassword())).build();
            voterService.addVoter(voter);
        } catch (DataIntegrityViolationException e) {
            voter = Voter.builder().build();
            log.trace("not added voter={}", voter);
        }

        return new VoterDTO(voter);
    }

    @RequestMapping(value = "/preloadVoters", method = RequestMethod.GET)
    public String preloadVoters(){

        Voter voter1;
        Voter voter2;
        Voter voter3;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            voter1 = Voter.builder().username("1234567891012").password(encoder.encode("parola")).enabled(true).build();
            voter2 = Voter.builder().username("1234567891013").password(encoder.encode("parola")).enabled(true).build();
            voter3 = Voter.builder().username("1234567891014").password(encoder.encode("parola")).enabled(true).build();

            voterService.addVoter(voter1);
            voterService.addVoter(voter2);
            voterService.addVoter(voter3);
        } catch (DataIntegrityViolationException e) {
            log.trace("voter exception",e);
        }

        return "Succes";
    }

}
