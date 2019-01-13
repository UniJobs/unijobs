package web.controller;

import core.model.Skill;
import core.repository.SkillRepository;
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
import web.dto.SkillDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SkillController.class})
@WebAppConfiguration
public class SkillControllerTest {


    @Autowired
    private WebApplicationContext wac;


    @Autowired
    private SkillRepository skillRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .dispatchOptions(true)
                .build();

        skillRepository.deleteAll();
    }

    @Test
    public void getAllReturns0SkillsWhenThereAre0SkillsInDb() throws Exception {
        mockMvc.perform(get("/api/skill/skills")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$skills", Matchers.hasSize(0)));
    }

    @Test
    public void getAllReturns1SkillWhenThereIs1SkillInDb() throws Exception {
        skillRepository.save(new Skill("salut"));

        mockMvc.perform(get("/api/skill/skills")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$skills", Matchers.hasSize(1)));
    }

}