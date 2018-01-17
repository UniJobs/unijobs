package web.dto;

import core.model.Job;
import core.model.Request;
import core.model.UniUser;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RequestDTO {
    private int id;
    private UniUserDTO fromUniUser;
    private Integer job;
    private UniUserDTO toUniUser;
    private String status;

    public RequestDTO(Request request){
        id = request.getId();
        fromUniUser = new UniUserDTO(request.getFromUniUser());
        job = request.getJob().getId();
        toUniUser = new UniUserDTO(request.getToUniUser());
        status = request.getStatus();
    }
}
