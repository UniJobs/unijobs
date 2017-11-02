package web.controller;

import core.model.Authority;
import core.model.UniUser;
import core.service.AuthorityService;
import core.service.FetchService;
import core.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.dto.UniUserDTO;
import web.dtos.UniUsersDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/user")
public class UniUserController {
    private static final Logger log = LoggerFactory.getLogger(UniUserController.class);

    @Autowired
    FetchService fetchService;

    @Autowired
    ManageService manageService;

    @Autowired
    AuthorityService authorityService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    @Transactional
    public UniUsersDTO getUsers(){
        return new UniUsersDTO(fetchService.getAllUsers());
    }

    @RequestMapping(value = "/getUserById")
    public UniUserDTO getUserById(@RequestParam("userId") Integer userId){
        log.trace("Get user by id : id={}",userId);
        UniUser uniUser = fetchService.getUserById(userId);
        log.trace("user returned by id={} is user={}",userId,uniUser);
        return new UniUserDTO(uniUser);

    }

    @RequestMapping(value = "/getUserByName")
    @Transactional
    public UniUserDTO getUniUserByUsername(@RequestParam(value = "username", required = true) String username) {
        System.out.println("username" + username);

        UniUser u = fetchService.getUserByUsername(username);

        UniUserDTO result = new UniUserDTO(u);
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public UniUserDTO addUser(
            @RequestBody final UniUserDTO userDTO){

        log.trace("addUser: userDto={}", userDTO);
        UniUser user;
        user = fetchService.getUserByUsername(userDTO.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            user = UniUser.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(encoder.encode(userDTO.getPassword()))
                    .dob(null)
                    .firstname(null)
                    .lastname(null)
                    .build();
            manageService.addUser(user);
        } catch (DataIntegrityViolationException e) {
            user = UniUser.builder().build();
            log.trace("not added user={}", user);
        }
        return new UniUserDTO(user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public UniUserDTO updateUser(
            @RequestBody final UniUserDTO userDTO){
        log.trace("updateUser: userId={}, userDTO={}", userDTO.getId(), userDTO);

        UniUser user;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy");
        try {
            user = UniUser.builder()
                    .id(userDTO.getId())
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(encoder.encode(userDTO.getPassword()))
                    .dob(formatter.parse(userDTO.getDob()))
                    .firstname(userDTO.getFirstname())
                    .lastname(userDTO.getLastname())
                    .enabled(true)
                    .build();
            manageService.updateUser(user);
            Authority authority = new Authority(user.getUsername(),"USER");
            authorityService.addAuthority(authority);
        } catch (DataIntegrityViolationException | ParseException e) {
            user = UniUser.builder().build();
            log.trace("not updated user={}", user);
        }
        return new UniUserDTO(user);
    }

}
