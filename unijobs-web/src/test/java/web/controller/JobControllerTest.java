package web.controller;

import core.model.Job;
import core.model.Skill;
import core.model.UniUser;
import core.repository.SkillRepository;
import core.repository.UniJobRepository;
import core.repository.UniUserRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class JobControllerTest extends TestBase {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UniUserRepository uniUserRepository;

    @Autowired
    private UniJobRepository uniJobRepository;

    @Before
    public void setup() {
        super.setup();


        uniJobRepository.deleteAll();
        uniUserRepository.deleteAll();
        skillRepository.deleteAll();



    }


    @Test
    public void getAllJobsForUserReturns0JobsForAUserWithNoSkilz() throws Exception {
        UniUser user = uniUserRepository.save(new UniUser("username", "password", "email", "firstname", "lastname", Date.from(Instant.now()), "phone"));

        uniJobRepository.save(Job.builder()
                        .description("salut")
                        .location(null)
                        .hoursPerWeek(1)
                        .cost(0)
                        .startDate(Date.from(Instant.now()))
                        .endDate(Date.from(Instant.now()))
                        .uniUser(user)
                        .skills(new ArrayList<>()).build()
        );

        mockMvc.perform(get("/api/job/getAllJobsForUser/" + user.getId() + "/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$jobs", Matchers.hasSize(0)));
    }

    @Test
    public void getAllJobsFilteredForUserReturns0JobsForUserWith1Job() throws Exception {


        UniUser user = UniUser.builder()
                .username("username")
                .password("password")
                .email("email")
                .firstname("firstname")
                .lastname("lastname")
                .dob(Date.from(Instant.now()))
                .phone("phone")
                .skills(new ArrayList<>()).build();
        uniUserRepository.save(user);




        Job job = Job.builder()
                .description("salut")
                .location("loc")
                .hoursPerWeek(1)
                .cost(0)
                .startDate(Date.from(Instant.now()))
                .endDate(Date.from(Instant.now()))
                .uniUser(user)
                .skills(new ArrayList<>())
                .build();




        uniJobRepository.save(job);



        mockMvc.perform(get("/api/job/jobs/" + user.getId() + "/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$jobs", Matchers.hasSize(0)));
    }

    @Test
    public void getAllJobsFilteredForUserReturns1JobsForUserWith1Job() throws Exception {


        UniUser user = UniUser.builder()
                .username("username")
                .password("password")
                .email("email")
                .firstname("firstname")
                .lastname("lastname")
                .dob(Date.from(Instant.now()))
                .phone("phone")
                .skills(new ArrayList<>()).build();
        uniUserRepository.save(user);


        UniUser user2 = UniUser.builder()
                .username("username2")
                .password("password")
                .email("email")
                .firstname("firstname")
                .lastname("lastname")
                .dob(Date.from(Instant.now()))
                .phone("phone")
                .skills(new ArrayList<>()).build();
        uniUserRepository.save(user2);


        Job job = Job.builder()
                .description("salut")
                .location("loc")
                .hoursPerWeek(1)
                .cost(0)
                .startDate(Date.from(Instant.now()))
                .endDate(Date.from(Instant.now()))
                .uniUser(user)
                .skills(new ArrayList<>())
                .build();


        uniJobRepository.save(job);

        mockMvc.perform(get("/api/job/jobs/" + user2.getId() + "/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$jobs", Matchers.hasSize(1)));
    }



    @Test
    public void newJobAddsJobToDB() throws Exception {

        UniUser user = UniUser.builder()
                .username("username")
                .password("password")
                .email("email")
                .firstname("firstname")
                .lastname("lastname")
                .dob(Date.from(Instant.now()))
                .phone("phone")
                .skills(new ArrayList<>()).build();
        uniUserRepository.save(user);


        String body = "{"
                + "\"description\":\"descr\","
                + "\"location\":\"loc\","
                + "\"hpw\":1,"
                + "\"cost\":0,"
                + "\"startDate\":\"2019-01-09\","
                + "\"endDate\":\"2019-01-27\","
                + "\"uniUserId\":\"" +user.getId() +"\","
                + "\"skillIds\":[]"
                + "}"
                ;

        mockMvc.perform(post("/api/job/newJob")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$description", Matchers.is("descr")));
    }

}
