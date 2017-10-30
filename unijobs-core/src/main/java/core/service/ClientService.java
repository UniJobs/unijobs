package core.service;

import core.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 10/30/2017.
 */

public interface ClientService {
    List<Client> getAll();
    Client getOne(int id);
    void insert(Client client);
}
