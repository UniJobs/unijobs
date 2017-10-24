package web.controller;

import core.service.FetchService;
import core.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dtos.UniUsersDTO;

@RestController
public class UniUserController {
    @Autowired
    FetchService fetchService;

    @Autowired
    ManageService manageService;

    @RequestMapping(value="users", method = RequestMethod.GET)
    public UniUsersDTO getUsers(){
        return new UniUsersDTO(fetchService.getAllUsers());
    }
}
