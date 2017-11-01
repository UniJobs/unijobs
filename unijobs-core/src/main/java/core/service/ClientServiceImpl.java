package core.service;

import core.model.Client;
import core.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 10/30/2017.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    ClientRepository repository;

    @Override
    public List<Client> getAll() {
        log.trace("get all clients");
        List<Client> res = repository.findAll();
        log.trace("got them all");
        return res;
    }

    @Override
    public Client getOne(int id) {
        log.trace("get one client {}", id);
        Client c = repository.findOne(id);
        log.trace("got him {}", c);
        return c;
    }

    @Override
    public void insert(Client client) {
        log.trace("inserting client {}", client);
        repository.save(client);
        log.trace("inserted him");
    }

    @Override
    public void clear(){
        log.trace("clear Clients");
        repository.deleteAll();
        log.trace("Clients cleared");
    }
}
