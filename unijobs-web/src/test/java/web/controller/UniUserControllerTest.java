package web.controller;

import core.model.UniUser;
import core.repository.UniUserRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import web.config.WebConfig;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SkillController.class, UniUserController.class})
@WebAppConfiguration
public class UniUserControllerTest {

    @Autowired
    private WebApplicationContext wac;


    @Autowired
    private UniUserRepository uniUserRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .dispatchOptions(true)
                .build();

        uniUserRepository.deleteAll();
    }

    @Test
    public void getAllReturns0UsersWhenThereAre0UsersInDb() throws Exception {
        mockMvc.perform(get("/api/user/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$users", Matchers.hasSize(0)));
    }

    @Test
    public void getAllReturns1UserWhenThereIs1UserInDb() throws Exception {
        uniUserRepository.save(new UniUser("username", "password", "email", "firstname", "lastname", Date.from(Instant.now()), "phone"));

        mockMvc.perform(get("/api/user/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$users", Matchers.hasSize(1)));
    }

}
