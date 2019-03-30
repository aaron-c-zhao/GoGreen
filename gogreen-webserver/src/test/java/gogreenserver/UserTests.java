package gogreenserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreenserver.entity.User;
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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class UserTests {

    // for debugging purposes
    private static final Logger LOGGER = LogManager.getLogger("Tests");

    // the ObjectMapper that Spring uses for its object->json conversions
    @Autowired
    private ObjectMapper mapper;

    // DON'T AUTOWIRE, IT IS SET UP MANUALLY
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    /**
     * Check if /api/user/{username} works.
     */
    @WithMockUser
    @Test
    public void checkUsers() throws Exception {

        LOGGER.debug("=== checkUsers() ===");

        User dummy = manager.persistAndFlush(createDummyUser("Alice"));
        RequestBuilder ereq = MockMvcRequestBuilders
            .get("/api/user/findUser/" + dummy.getUsername())
            .accept(MediaType.APPLICATION_JSON);

        MvcResult eres = mockMvc.perform(ereq).andExpect(status().is(200)).andReturn();

        // if user exist.
        assertThat(eres.getResponse().getContentAsString()).isEqualTo("success");

        RequestBuilder nreq = MockMvcRequestBuilders.get("/api/user/findUser/Bob")
            .accept(MediaType.APPLICATION_JSON);
        MvcResult nres = mockMvc.perform(nreq).andExpect(status().is(404)).andReturn();

        // if user does not exist.
        assertThat(nres.getResponse().getContentAsString()).isEqualTo("fail");

        manager.clear();
    }

    @Test
    public void addUser() throws Exception {

        LOGGER.debug("=== addUser() ===");

        User dummy = createDummyUser("Danny");
        RequestBuilder req = MockMvcRequestBuilders.post("/api/createUser")
            .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));
        mockMvc.perform(req).andExpect(status().is(200));

        User found = manager.find(User.class, dummy.getUsername());

        // /api/createUser properly salts and hashes the user password, and therefore it
        // is (or should be) impossible to recreate it manually.
        dummy.setPassword(found.getPassword());

        assertThat(found).isEqualToComparingFieldByField(dummy);

        manager.clear();
    }

    @WithMockUser("Emma")
    @Test
    public void deleteUser() throws Exception {
        User dummy = manager.persistAndFlush(createDummyUser("Emma"));
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/deleteUser/" + dummy.getUsername())
            .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));
        mockMvc.perform(req).andExpect(status().is(200));

        assertThat(manager.find(User.class, dummy.getUsername())).isNull();
    }

    private User createDummyUser(String name) {
        Random rgn = new Random(name.hashCode());
        return new User(name, "pass" + name, name + "@example.com",
            LocalDate.of(1950 + rgn.nextInt(60), rgn.nextInt(12) + 1, rgn.nextInt(29)),
            LocalDate.now());
    }
}
