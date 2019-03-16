package gogreenserver;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class UserControllerTests {

    // for debugging purposes
    private static final Logger LOGGER = LogManager.getLogger("Tests");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController controller;

    /**
     * Setup mock db.
     */
    @Before
    public void setupH2() {
        controller.addUser(createDummyUser("Alice"));
        controller.addUser(createDummyUser("Bob"));
        controller.addUser(createDummyUser("Charlie"));
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

        LOGGER.debug(list);

        for (JsonNode user : list) {
            LOGGER.debug(user);
            RequestBuilder userReq = MockMvcRequestBuilders.get("/api/users/" + user.asText())
                    .accept(MediaType.APPLICATION_JSON);
            mockMvc.perform(userReq).andExpect(status().is(200)).andReturn();
        }
    }
}
