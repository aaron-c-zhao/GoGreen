package gogreenclient.datamodel;

import gogreenclient.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAccountValidatorTest {

    @Autowired
    private UserAccountValidator validator;

    private MockRestServiceServer server;


    @Test
    public void usernameNull() {
        boolean exception = false;
        try {
            validator.accountValidate("", "abc", "abc", LocalDate.now(), "abc");
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void usernameNullLogin() {
        boolean exception = false;
        try {
            validator.loginValidate("", "abc");
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void passwordNull() {
        boolean exception = false;
        try {
            validator.accountValidate("abc", "", "abc", LocalDate.now(), "abc");
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void passwordNullLogin() {
        boolean exception = false;
        try {
            validator.loginValidate("abc", "");
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void passwordShort() {
        boolean exception = false;
        try {
            validator.accountValidate("abc", "abc", "abc", LocalDate.now(), "abc");
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void passwordNotMatch() {
        boolean exception = false;
        try {
            validator.accountValidate("abc", "abcabcccd", "abcabcc", LocalDate.now(), "abc");
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void usernameExists() throws Exception {
        RestTemplate template = new RestTemplate();
        validator.getUserModel().setRestTemplate(template);
        server = MockRestServiceServer.createServer(template);
        server.expect(requestTo(new URI("https://localhost:8443/api/user/findUser/Alin")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("success", MediaType.APPLICATION_JSON));
        String exception = "";
        try {
            validator.accountValidate("Alin", "abcabcc", "abcabcc", LocalDate.now(), "abc");
        } catch (Exception e) {
            exception = e.getMessage();
        }
        assertEquals("This user name is already used by other user.", exception);
    }

    @Test
    public void emailNotValid() throws Exception {
        RestTemplate template = new RestTemplate();
        validator.getUserModel().setRestTemplate(template);
        server = MockRestServiceServer.createServer(template);
        server.expect(requestTo(new URI("https://localhost:8443/api/user/findUser/Alin")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("fail", MediaType.APPLICATION_JSON));
        String exception = "";
        try {
            validator.accountValidate("Alin", "abcabcc", "abcabcc", LocalDate.now(), "ab ca");
        } catch (Exception e) {
            exception = e.getMessage();
        }
        assertEquals("Your email address is invalid", exception);
    }

    @Test
    public void emailEmpty() throws Exception {
        RestTemplate template = new RestTemplate();
        validator.getUserModel().setRestTemplate(template);
        server = MockRestServiceServer.createServer(template);
        server.expect(requestTo(new URI("https://localhost:8443/api/user/findUser/Alin")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("fail", MediaType.APPLICATION_JSON));
        String exception = "";
        try {
            validator.accountValidate("Alin", "abcabcc", "abcabcc", LocalDate.now(), "");
        } catch (Exception e) {
            exception = e.getMessage();
        }
        assertEquals("", exception);
    }

    @Test
    public void bdayFuture() throws Exception {
        RestTemplate template = new RestTemplate();
        validator.getUserModel().setRestTemplate(template);
        server = MockRestServiceServer.createServer(template);
        server.expect(requestTo(new URI("https://localhost:8443/api/user/findUser/Alin")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("fail", MediaType.APPLICATION_JSON));
        String exception = "";
        try {
            validator.accountValidate("Alin", "abcabcc", "abcabcc", LocalDate.of(2020, 12, 12), "abca@gmail.com");
        } catch (Exception e) {
            exception = e.getMessage();
        }
        assertEquals("Birthday must be in the past.", exception);
    }

    @Test
    public void bdayNull() throws Exception {
        RestTemplate template = new RestTemplate();
        validator.getUserModel().setRestTemplate(template);
        server = MockRestServiceServer.createServer(template);
        server.expect(requestTo(new URI("https://localhost:8443/api/user/findUser/Alin")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("fail", MediaType.APPLICATION_JSON));
        String exception = "";
        try {
            validator.accountValidate("Alin", "abcabcc", "abcabcc", null, "abca@gmail.com");
        } catch (Exception e) {
            exception = e.getMessage();
        }
        assertEquals("", exception);
    }

    @Test
    public void everythingFine() throws Exception {
        RestTemplate template = new RestTemplate();
        validator.getUserModel().setRestTemplate(template);
        server = MockRestServiceServer.createServer(template);
        server.expect(requestTo(new URI("https://localhost:8443/api/user/findUser/Alin")))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("fail", MediaType.APPLICATION_JSON));
        String exception = "";
        try {
            validator.accountValidate("Alin", "abcabcc", "abcabcc", LocalDate.now(), "abca@gmail.com");
        } catch (Exception e) {
            exception = e.getMessage();
        }
        assertEquals("", exception);
    }
}