package web.dtos;

import core.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web.dto.ClientDTO;

import java.util.List;

/**
 * Created by Alex on 10/30/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientsDTO {
    private List<ClientDTO> clientDTOS;
}
