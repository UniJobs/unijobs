package web.controller;

import core.model.UniUser;
import core.service.FetchService;
import core.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import web.dto.UniUserDTO;
import web.dtos.UniUsersDTO;

@RestController
public class UniUserController {
    private static final Logger log = LoggerFactory.getLogger(UniUserController.class);

    @Autowired
    FetchService fetchService;

    @Autowired
    ManageService manageService;

    @RequestMapping(value="users", method = RequestMethod.GET)
    public UniUsersDTO getUsers(){
        return new UniUsersDTO(fetchService.getAllUsers());
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
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

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public UniUserDTO updateUser(
            @PathVariable final Long userId,
            @RequestBody final UniUserDTO userDTO){
        log.trace("updateUser: userId={}, userDTO={}", userId, userDTO);

        UniUser user;
        try {
            user = UniUser.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .dob(userDTO.getDob())
                    .firstname(userDTO.getFirstname())
                    .lastname(userDTO.getLastname())
                    .build();
            manageService.updateUser(userId, user);
        } catch (DataIntegrityViolationException e) {
            user = UniUser.builder().build();
            log.trace("not updated user={}", user);
        }
        return new UniUserDTO(user);
    }

}
