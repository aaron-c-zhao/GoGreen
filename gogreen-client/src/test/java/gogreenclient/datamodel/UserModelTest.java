package gogreenclient.datamodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreenclient.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserModelTest {

    private RestTemplate template = new RestTemplate();
    @Autowired
    private UserModel userModel;
    @Autowired
    private ObjectMapper objectMapper;
    private MockRestServiceServer server;
    private User userz;
    private String user;

    @Before
    public void setUp() throws Exception {
        userModel.setRestTemplate(template);
        userz = new User();
        userz.setUsername("Alin");
        userz.setPassword("alin");
        userz.setDateCreated(LocalDate.now());
        user = objectMapper.writeValueAsString(userz);
        server = MockRestServiceServer.createServer(template);
        server.expect(requestTo(new URI("https://localhost:8443/api/createUser")))
            .andExpect(method(HttpMethod.POST))
//            .andExpect(content().string(user))
            .andRespond(withSuccess(user, MediaType.APPLICATION_JSON));
    }

    @Test
    public void addUser() throws Exception {
        assertEquals(userz.getUsername(), userModel
            .addUser(userz.getUsername(), userz.getPassword(), null, null).getBody().getUsername());
    }

    @Test
    public void findUser() throws Exception {
        server.reset();
        server.expect(requestTo(new URI("https://localhost:8443/api/user/findUser/Alin")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("success", MediaType.APPLICATION_JSON));
        assertTrue(userModel.findUser("Alin"));
    }
}