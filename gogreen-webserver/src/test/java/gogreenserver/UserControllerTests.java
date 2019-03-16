package gogreenserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gogreenserver.controllers.UserController;
import gogreenserver.entity.User;

import net.bytebuddy.utility.RandomString;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
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
public class UserControllerTests {

    // for debugging purposes
    private static final Logger LOGGER = LogManager.getLogger("Tests");
    private static User[] dummyUsers;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager manager;

    /**
     * Setup mock db.
     */
    @Before
    public void setupH2() {
        dummyUsers = new User[3];
        dummyUsers[0] = manager.persist(createDummyUser("alice"));
        dummyUsers[1] = manager.persist(createDummyUser("bob"));
        dummyUsers[2] = manager.persist(createDummyUser("charlie"));
        LOGGER.debug("Set up the 3 dummy users");
    }

    private User createDummyUser(String name) {
        Random rgn = new Random(name.hashCode());
        return new User(name, "pass" + name, name + "@example.com", "First" + name, "Last" + name,
                LocalDate.of(1950 + rgn.nextInt(60), rgn.nextInt(13), rgn.nextInt(29)),
                RandomString.hashOf(name.hashCode()));
    }

    @Test
    public void checkUsers() throws Exception {
        RequestBuilder listReq = MockMvcRequestBuilders.get("/api/users")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult res = mockMvc.perform(listReq).andExpect(status().is(200)).andReturn();

        JsonNode list = (new ObjectMapper()).readTree(res.getResponse().getContentAsString());

        LOGGER.debug("Returned Json: " + list);

        int usercount = 0;
        for (JsonNode user : list) {
            LOGGER.debug("User " + usercount + ": " + user);
            RequestBuilder userReq = MockMvcRequestBuilders
                    .get("/api/user/" + user.get("username").asText())
                    .accept(MediaType.APPLICATION_JSON);
            MvcResult ures = mockMvc.perform(userReq).andExpect(status().is(200)).andReturn();

            usercount++;
        }
        LOGGER.debug("User amount: " + usercount);
        assertThat(usercount).isEqualTo(3);
    }
}
