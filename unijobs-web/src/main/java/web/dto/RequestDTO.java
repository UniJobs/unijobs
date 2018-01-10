package web.dto;

import core.model.Request;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RequestDTO {
    private int id;
    private Integer fromUniUser;
    private Integer job;
    private Integer toUniUser;
    private String status;

    public RequestDTO(Request request){
        id = request.getId();
        fromUniUser = request.getFromUniUser().getId();
        job = request.getJob().getId();
        toUniUser = request.getToUniUser().getId();
        status = request.getStatus();
    }
}
