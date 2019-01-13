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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void getAllJobsForUserReturns1JobForAUserWithSkillz() throws Exception {
        Skill skill = skillRepository.save(Skill.builder().description("a").build());
        ArrayList skills = new ArrayList<Skill>();
        skills.add(skill);
        UniUser user = new UniUser("username", "password", "email", "firstname", "lastname", Date.from(Instant.now()), "phone");
        user.setSkills(skills);
        UniUser added = uniUserRepository.save(user);

        uniJobRepository.save(Job.builder()
                        .description("salut")
                        .location(null)
                        .hoursPerWeek(1)
                        .cost(0)
                        .startDate(Date.from(Instant.now()))
                        .endDate(Date.from(Instant.now()))
                        .uniUser(null)
                        .skills(skills).build()
        );

        mockMvc.perform(get("/api/job/getAllJobsForUser/" + added.getId() + "/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$jobs", Matchers.hasSize(1)));
    }

}
