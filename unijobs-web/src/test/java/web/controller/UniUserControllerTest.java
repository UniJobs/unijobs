package web.controller;

import core.model.UniUser;
import core.repository.UniJobRepository;
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
import org.springframework.web.util.JavaScriptUtils;
import web.config.WebConfig;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UniUserControllerTest extends TestBase {

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

    @Test
    public void getUniUserByIdReturns1UserById() throws Exception {
        UniUser user = uniUserRepository.save(new UniUser("username", "password", "email", "firstname", "lastname", Date.from(Instant.now()), "phone"));

        mockMvc.perform(get("/api/user/getUserById?userId=" + user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$id", Matchers.is(user.getId())));
    }

    @Test
    public void getUniUserByUsernameReturns1UserByUsername() throws Exception {
        uniUserRepository.save(new UniUser("username", "password", "email", "firstname", "lastname", Date.from(Instant.now()), "phone"));

        mockMvc.perform(get("/api/user/getUserByName?username=username")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$email", Matchers.is("email")));
    }

    @Test
    public void newUserAddsUserToDB() throws Exception {
        String body = "{"
                + "\"username\":\"username\","
                + "\"password\":\"password\","
                + "\"email\":\"email\","
                + "\"firstname\":\"firstname\","
                + "\"lastname\":\"lastname\","
                + "\"dob\":\"1996-04-02\","
                + "\"phone\":\"phone\","
                + "\"skills\":[]"
                + "}"
        ;

        mockMvc.perform(post("/api/user/newUser")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$email", Matchers.is("email")));
    }

    @Test
    public void updateUserUpdatesUser() throws Exception {
        UniUser user = uniUserRepository.save(new UniUser("username", "password", "email", "firstname", "lastname", Date.from(Instant.now()), "phone"));

        String body = "{"
                + "\"id\":\"" + user.getId() + "\","
                + "\"username\":\"username\","
                + "\"password\":\"password\","
                + "\"email\":\"email2\","
                + "\"firstname\":\"firstname\","
                + "\"lastname\":\"lastname\","
                + "\"dob\":\"1996-04-02\","
                + "\"phone\":\"phone\","
                + "\"skills\":[]"
                + "}"
        ;

        mockMvc.perform(post("/api/user/updateUser")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$email", Matchers.is("email2")));
    }

}
