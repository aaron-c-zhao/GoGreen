package gogreenserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Autowired
    private UserDetailsService userfinder;

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

    @WithMockUser("Ben")
    @Test
    public void updateUser() throws Exception {

        LOGGER.debug("=== updateUser() ===");

        User dummy = manager.persistAndFlush(createDummyUser("Ben"));
        User newdummy = new User();
        newdummy.setUsername(dummy.getUsername());
        newdummy.setPassword(dummy.getPassword());
        newdummy.setEmail(RandomString.make());

        LOGGER.debug("Old user: " + dummy);
        LOGGER.debug("User change: " + newdummy);

        RequestBuilder req = MockMvcRequestBuilders.post("/api/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newdummy));

        LOGGER.debug("Old user just before req: " + dummy);
        mockMvc.perform(req).andExpect(status().is(200));
        // so apparently, our dummy object will be updated after db change.
        LOGGER.debug("Old user just after req: " + dummy);

        User found = manager.find(User.class, dummy.getUsername());

        LOGGER.debug("Merged User: " + found);

        assertThat(found).isEqualToComparingFieldByField(dummy);

        manager.clear();
    }

    @Test
    public void addUsernameTaken() throws Exception {

        LOGGER.debug("=== addUsernameTaken() ===");

        User dummy = manager.persistAndFlush(createDummyUser("BoatyMcBoatFace"));

        RequestBuilder req = MockMvcRequestBuilders.post("/api/createUser")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));

        mockMvc.perform(req).andExpect(status().is(400));

        manager.clear();
    }

    @WithMockUser("Hackerman")
    @Test
    public void updateWitoutPermission() throws Exception {

        LOGGER.debug("=== updateWitoutPermission() ===");

        User dummy = manager.persistAndFlush(createDummyUser("TrainMcTrainface"));

        RequestBuilder req = MockMvcRequestBuilders.post("/api/createUser")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));

        mockMvc.perform(req).andExpect(status().is(400));

        manager.clear();
    }

    @WithMockUser("Emma")
    @Test
    public void deleteUser() throws Exception {

        LOGGER.debug("=== deleteUser() ===");

        User dummy = manager.persistAndFlush(createDummyUser("Emma"));
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/deleteUser/" + dummy.getUsername())
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));
        mockMvc.perform(req).andExpect(status().is(200));

        assertThat(manager.find(User.class, dummy.getUsername())).isNull();

        manager.clear();
    }

    @WithMockUser("Hackerman")
    @Test
    public void deleteWithoutPermission() throws Exception {

        LOGGER.debug("=== deleteWithoutPermission() ===");

        User dummy = manager.persistAndFlush(createDummyUser("Emma"));
        RequestBuilder req = MockMvcRequestBuilders.delete("/api/deleteUser/" + dummy.getUsername())
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));
        mockMvc.perform(req).andExpect(status().is(401));

        assertThat(manager.find(User.class, dummy.getUsername())).isNotNull();

        manager.clear();
    }

    @WithMockUser
    @Test
    public void login() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/login/");
        mockMvc.perform(req).andExpect(status().is(200));
    }

    @Test
    public void checkAuthChecker() {

        User dummy = createDummyUser("Yeet");

        assertThatThrownBy(() -> {
            userfinder.loadUserByUsername(dummy.getUsername());
        }).isInstanceOf(UsernameNotFoundException.class);
        
        manager.persistAndFlush(dummy);
        
        assertThat(userfinder.loadUserByUsername(dummy.getUsername())).isNotNull();
    }

    /**
     * Creates a mock User instance.
     * 
     * @param name The username.
     */
    public static User createDummyUser(String name) {
        Random rgn = new Random(name.hashCode());
        return new User(name, "pass" + name, name + "@example.com",
                LocalDate.of(1950 + rgn.nextInt(60), rgn.nextInt(12) + 1, rgn.nextInt(29)),
                LocalDate.now(), "http://example.com");
    }
}
