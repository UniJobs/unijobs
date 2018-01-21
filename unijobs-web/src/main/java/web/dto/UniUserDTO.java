package web.dto;

import core.model.Recommendation;
import core.model.Skill;
import core.model.UniUser;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UniUserDTO {
    public Integer id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String dob;
    private String phone;
    private List<Integer> sentReviews = new ArrayList<>();
    private List<Integer> receivedReviews = new ArrayList<>();
    private List<Integer> jobs = new ArrayList<>();
    private List<Integer> sentRequests = new ArrayList<>();
    private List<Integer> receivedRequests = new ArrayList<>();
    private List<Integer> sentRecommendations = new ArrayList<>();
    private List<Integer> receivedRecommendations = new ArrayList<>();
    private List<Integer> mentionedRecommendations = new ArrayList<>();
    private List<Integer> skills = new ArrayList<>();

    //The reviews lists were not added now after a stand-up with Bogdan and SCRUM Master, Alexandra!!!

    public UniUserDTO(UniUser user){
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        if(user.getDob()!=null) {
            DateFormat formatterDB = new SimpleDateFormat("yyyy-MM-dd");
            dob = formatterDB.format(user.getDob());
        }
        phone  = user.getPhone();
        skills = user.getSkills().stream().map(Skill::getId).collect(Collectors.toList());
    }
}
