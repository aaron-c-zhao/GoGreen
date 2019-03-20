package gogreenclient.datamodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreenclient.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserCareerServiceTest {

    @Autowired
    private ObjectMapper objectMapper;
    private RestTemplate template = new RestTemplate();
    private UserCareerService service = new UserCareerService();
    private MockRestServiceServer server;
    private String uri = "https://localhost:8443/api/career";


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        server = MockRestServiceServer.createServer(template);
        service.setRestTemplate(template);
    }

    @Test
    public void getCareer_UserExists() throws Exception {
        UserCareer userCareer = new UserCareer();
        userCareer.setUsername("zhao");
        service.setUsername("zhao");
        String userCareer_json = objectMapper.writeValueAsString(userCareer);
        this.setUp_Get(server, uri + "/" + "zhao", userCareer_json);
        assertEquals(userCareer.getUsername(), service.getCareer().getUsername());
    }

    @Test
    public void getCareer_NewUser() throws Exception{
        UserCareerService spyService = spy(UserCareerService.class);
        spyService.setRestTemplate(template);
        UserCareer im_null = null;
        String imnull_json = objectMapper.writeValueAsString(im_null);
        UserCareer newOne = new UserCareer();
        spyService.setUsername("Aladdin");
        newOne.setUsername("Aladdin");
        newOne.setCo2saved(0);
        doReturn(new ResponseEntity<>(newOne, HttpStatus.OK)).when(spyService).createUserCareer();
        setUp_Get(server, uri + "/" + "Aladdin", imnull_json);
        assertEquals(newOne.getUsername(), spyService.getCareer().getUsername());
    }

    @Test()
    public void getCareerBadRequest() throws Exception{
        UserCareerService spyService = spy(UserCareerService.class);
        spyService.setRestTemplate(template);
        UserCareer im_null = null;
        UserCareer newOne = new UserCareer();
        newOne.setUsername("Aladdin");
        spyService.setUsername("Aladdin");
        String null_json = objectMapper.writeValueAsString(im_null);
        doReturn(new ResponseEntity<>(newOne, HttpStatus.BAD_REQUEST)).when(spyService).createUserCareer();
        setUp_Get(server, uri + "/" + "Aladdin", null_json);
        boolean thrown = false;
        try{
            spyService.getCareer();
            fail();
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void createUserCareer() throws Exception{
        service.setUsername("Gregor");
        UserCareer career = new UserCareer();
        career.setUsername("Gregor");
        career.setCo2saved(0);
        String gregor_json = objectMapper.writeValueAsString(career);
        setUp_Post(server, uri, gregor_json, gregor_json);
        assertEquals(career.getUsername(), service.createUserCareer().getBody().getUsername());
    }

    @Test
    public void updateUserCareer() throws Exception{
        UserCareerService spyService = spy(UserCareerService.class);
        spyService.setRestTemplate(template);
        UserCareer userCareer = new UserCareer();
        userCareer.setUsername("El");
        userCareer.setCo2saved(15);
        UserCareer updated = new UserCareer();
        updated.setCo2saved(20);
        updated.setUsername("El");
        String updatedJson = objectMapper.writeValueAsString(updated);
        doReturn(userCareer).when(spyService).getCareer();
        setUp_Post(server, uri + "update", updatedJson, updatedJson);
        assertEquals(20, spyService.updateUserCareer(5).getCo2saved());
    }

    @Test
    public void updateUserCareer_BadRequest() throws Exception{
        UserCareerService spyService = spy(UserCareerService.class);
        spyService.setRestTemplate(template);
        UserCareer userCareer = new UserCareer();
        userCareer.setUsername("El");
        userCareer.setCo2saved(15);
        UserCareer updated = new UserCareer();
        updated.setUsername("El");
        updated.setCo2saved(20);
        String updatedJson = objectMapper.writeValueAsString(updated);
        doReturn(userCareer).when(spyService).getCareer();
        server.expect(requestTo(uri + "update"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(updatedJson))
                .andRespond(withStatus(HttpStatus.BAD_REQUEST));
        boolean error = false;
        try {
            spyService.updateUserCareer(5);
        } catch (Exception e) {
            error = true;
        }
        assertTrue(error);
    }

    public void setUp_Post(MockRestServiceServer server, String uri, String content, String json) throws Exception {
        server.reset();
        server.expect(requestTo(uri))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().string(content))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
    }

    public  void setUp_Get(MockRestServiceServer server, String uri, String json) throws Exception {
        server.reset();
        server.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
    }
}