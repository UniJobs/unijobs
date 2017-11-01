package web.controller;

import core.model.UniUser;
import core.service.FetchService;
import core.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.dto.UniUserDTO;
import web.dtos.UniUsersDTO;

@RestController
@RequestMapping("/api/user")
public class UniUserController {
    private static final Logger log = LoggerFactory.getLogger(UniUserController.class);

    @Autowired
    FetchService fetchService;

    @Autowired
    ManageService manageService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public UniUsersDTO getUsers(){
        return new UniUsersDTO(fetchService.getAllUsers());
    }

    @RequestMapping(value = "/getUserById")
    public UniUserDTO getUserById(@RequestParam("userId") Integer userId){
        log.trace("Get user by id : id={}",userId);
        UniUser uniUser = fetchService.getOneById(userId);
        log.trace("user returned by id={} is user={}",userId,uniUser);
        return new UniUserDTO(uniUser);

    }

    @RequestMapping(value = "/getUserByName")
    @Transactional
    public UniUserDTO getUniUserByUsername(@RequestParam(value = "username", required = true) String username) {
        System.out.println("username" + username);

        UniUser u = fetchService.getOneByUsername(username);

        UniUserDTO result = new UniUserDTO(u.getId(),u.getUsername(),u.getPassword(),u.getEmail(),u.getFirstname(),u.getLastname(),u.getDob());
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public UniUserDTO addUser(
            @RequestBody final UniUserDTO userDTO){

        log.trace("addUser: userDto={}", userDTO);
        UniUser user;
        try {
            user = UniUser.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
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
        try {
            user = UniUser.builder()
                    .id(userDTO.getId())
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .dob(userDTO.getDob())
                    .firstname(userDTO.getFirstname())
                    .lastname(userDTO.getLastname())
                    .build();
            manageService.updateUser(user);
        } catch (DataIntegrityViolationException e) {
            user = UniUser.builder().build();
            log.trace("not updated user={}", user);
        }
        return new UniUserDTO(user);
    }

}
