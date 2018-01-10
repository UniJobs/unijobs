package web.dtos;

import core.model.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import web.dto.RequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class RequestsDTO {
    private List<RequestDTO> requests;

    public RequestsDTO(List<Request> requests){
        this.requests = requests.stream().map(RequestDTO::new).collect(Collectors.toList());
    }

    public void add(RequestDTO requestDTO){
        requests.add(requestDTO);
    }
}
