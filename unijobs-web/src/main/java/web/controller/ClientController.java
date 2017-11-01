package web.controller;

import core.service.ClientService;
import core.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dto.ClientDTO;
import web.dtos.ClientsDTO;

import java.util.stream.Collectors;

/**
 * Created by Alex on 10/30/2017.
 */

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value="clients", method = RequestMethod.GET)
    public ClientsDTO getAll(){
        log.trace("client controller - get all clients");
        ClientsDTO res = new ClientsDTO(clientService.getAll().stream().map(c -> new ClientDTO(c)).collect(Collectors.toList()));
        log.trace("client controller - got them all");
        return res;
    }

}
