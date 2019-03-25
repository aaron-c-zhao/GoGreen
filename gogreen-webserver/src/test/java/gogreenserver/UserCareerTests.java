package gogreenserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gogreenserver.entity.User;
import gogreenserver.entity.UserCareer;
import gogreenserver.services.UserCareerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)

//set up test mvc
@AutoConfigureWebMvc
@AutoConfigureMockMvc

//set up test db
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class UserCareerTests {

    private static final Logger LOGGER = LogManager.getLogger("Tests");

    // the ObjectMapper that Spring uses for its object->json conversions
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private UserCareerService service;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @WithMockUser
    @Test
    public void checkCareers() throws Exception {
        
        LOGGER.debug("=== checkCareers() ===");
        
        UserCareer[] dummyCareers = new UserCareer[3];
        dummyCareers[0] = manager.persist(createDummyCareer("Alice"));
        dummyCareers[1] = manager.persist(createDummyCareer("Bob"));
        dummyCareers[2] = manager.persist(createDummyCareer("Charlie"));
        manager.flush();

        RequestBuilder listreq = MockMvcRequestBuilders.get("/api/career")
            .accept(MediaType.APPLICATION_JSON);

        MvcResult listres = mockMvc.perform(listreq).andExpect(status().is(200)).andReturn();

        JsonNode list = mapper.readTree(listres.getResponse().getContentAsString());

        LOGGER.debug("Returned Json: " + list);

        int counter = 0;
        for (JsonNode career : list) {
            LOGGER.debug("Career " + counter + ": " + career);
            RequestBuilder req = MockMvcRequestBuilders
                .get("/api/career/" + career.get("username").asText())
                .accept(MediaType.APPLICATION_JSON);
            MvcResult res = mockMvc.perform(req).andExpect(status().is(200)).andReturn();

            assertThat(res.getResponse().getContentAsString())
                .isEqualTo(mapper.writeValueAsString(dummyCareers[counter]));

            counter++;
        }

    }
    
    @WithMockUser
    @Test
    public void addCareer() throws Exception {
        
        LOGGER.debug("=== addCareer() ===");
        
        UserCareer dummy = createDummyCareer("Danny");
        RequestBuilder req = MockMvcRequestBuilders.post("/api/career")
            .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));
        mockMvc.perform(req).andExpect(status().is(200));

        UserCareer result = manager.find(UserCareer.class, dummy.getUsername());
        LOGGER.debug("Stored value: " + mapper.writeValueAsString(result));
        LOGGER.debug("Dummy value: " + mapper.writeValueAsString(dummy));

        assertThat(result).isEqualToComparingFieldByField(dummy);

        manager.clear();
    }

    @WithMockUser
    @Test
    public void removeCareer() throws Exception {
        
        LOGGER.debug("=== removeCareer() ===");
        
        String name = "Ellen";
        UserCareer dummy = createDummyCareer(name);
        manager.persistAndFlush(dummy);
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/career")
            .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));
        mockMvc.perform(req).andExpect(status().is(200));

        assertThat(manager.find(User.class, dummy.getUsername())).isNull();

        // TODO maybe check if the db is properly empty somehow?
        manager.clear();
    }
    
    @WithMockUser
    @Test
    public void updateCareer() throws Exception {
        
        LOGGER.debug("=== addCareer() ===");
        
        UserCareer dummy = createDummyCareer("Frank");
        LOGGER.debug("Old career: " + mapper.writeValueAsString(dummy));
        manager.persistAndFlush(dummy);

        // new career with a different co2 save
        UserCareer dummyNew = new UserCareer(dummy.getUsername(), dummy.getco2saved()
            + new Random(dummy.getUsername().hashCode()).nextInt(Integer.MAX_VALUE));

        LOGGER.debug("New career: " + mapper.writeValueAsString(dummyNew));

        RequestBuilder req = MockMvcRequestBuilders.post("/api/careerupdate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(dummyNew));

        mockMvc.perform(req).andExpect(status().is(200)).andReturn();

        UserCareer save = manager.find(UserCareer.class, dummy.getUsername());

        LOGGER.debug("Saved career: " + mapper.writeValueAsString(save));

        assertThat(dummyNew).isEqualToComparingFieldByField(save);
    }

    @WithMockUser
    @Test
    public void updateCareer_Null() {
        
        LOGGER.debug("=== updateCareer_Null() ===");
        
        UserCareer meHere = createDummyCareer("Andy");
        UserCareer imNotHere = createDummyCareer("Rudolph");
        service.createUserCareer(meHere);
        assertFalse(service.updateCareer(imNotHere));
    }

    private UserCareer createDummyCareer(String user) {
        return new UserCareer(user, new Random(user.hashCode()).nextInt(Integer.MAX_VALUE));
    }

}
