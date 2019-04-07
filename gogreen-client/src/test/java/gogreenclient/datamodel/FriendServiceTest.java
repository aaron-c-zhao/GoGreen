package gogreenclient.datamodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreenclient.config.AppConfig;
import gogreenclient.screens.ScreenConfiguration;
import org.apache.http.HttpException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FriendServiceTest {

    @Autowired
    private ObjectMapper mapper;

    ExceptionHandler handler = new ExceptionHandler();
    private MockRestServiceServer server;
    private FriendService friendService = new FriendService();
    private RestTemplate template = new RestTemplate();
    private String url = "https://localhost:8443/api/";
    private Messenger msg;

    @Before
    public void setUp() {
        msg = spy(Messenger.class);
        MockitoAnnotations.initMocks(this);
        server = MockRestServiceServer.createServer(template);
        friendService.setRestTemplate(template);
        friendService.setUrl("https://localhost:8443/api/");
        handler.setMessenger(msg);
        friendService.setExceptionHandler(handler);
        friendService.setUsername("gru");
    }

    @Test
    public void addFriendBadRequestTest() throws Exception {
        setUpAddFriend(new Friend(), HttpStatus.BAD_REQUEST);
        assertEquals(-1, friendService.addFriend("mike"));
    }

    @Test
    public void addFriendNullTest() throws Exception {
        setUpAddFriend(null, HttpStatus.BAD_REQUEST);
        assertEquals(-1, friendService.addFriend("mike"));
    }

    @Test
    public void addFriendInternalServerErrorTest() throws Exception {
        setUpAddFriend(new Friend(), HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.doNothing().when(msg).showMessage("Server error, please try again");
        assertEquals(-1, friendService.addFriend("mike"));
    }

    @Test
    public void addFriendNotFoundTest() throws Exception {
        setUpAddFriend(new Friend(), HttpStatus.NOT_FOUND);
        Mockito.doNothing().when(msg).showMessage("mike not found. Please try again.");
        assertEquals(-1, friendService.addFriend("mike"));
    }

    @Test
    public void addFriendAlreadyReportedTest() throws Exception {
        setUpAddFriend(new Friend(), HttpStatus.ALREADY_REPORTED);
        assertEquals(0, friendService.addFriend("mike"));
    }

    @Test
    public void addFriendOkTest() throws Exception {
        setUpAddFriend(new Friend(), HttpStatus.OK);
        assertEquals(1, friendService.addFriend("mike"));
    }

    @Test
    public void getFriendRecordsOk() throws Exception {
        setUpGetRecordsOk(HttpStatus.OK);
        assertEquals(new ArrayList<>(), friendService.getFriendRecords());
    }

    @Test
    public void getFriendRecordsBadRequest() throws Exception {
        setUpGetRecordsOk(HttpStatus.BAD_REQUEST);
        assertEquals(new ArrayList<>(), friendService.getFriendRecords());
    }

    @Test
    public void getFriendRecordsNull() throws Exception {
        setUpGetRecordsOk(HttpStatus.OK);
        assertEquals(new ArrayList<>(), friendService.getFriendRecords());
    }

    @Test
    public void getFriendRecordsInternalServerError() {
        setUpGetRecordsError(HttpStatus.INTERNAL_SERVER_ERROR, HttpServerErrorException.class);
        assertEquals(new ArrayList<>(), friendService.getFriendRecords());
    }

    @Test
    public void getFriendRecordsNotFound() throws Exception {
        setUpGetRecordsError(HttpStatus.NOT_FOUND, HttpClientErrorException.class);
        assertEquals(new ArrayList<>(), friendService.getFriendRecords());
    }

    public void setUpAddFriend(Friend friend, HttpStatus status) throws Exception {
        server.reset();
        String resp = mapper.writeValueAsString(friend);
        server.expect(requestTo(url + "friend"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(status).body(resp));
    }

    public void setUpGetRecordsError(HttpStatus status, Class error) {
        RestTemplate restTemplate = mock(RestTemplate.class);
        friendService.setRestTemplate(restTemplate);
        server.reset();
        server.expect(requestTo(url + "friend/record/gru"))
                .andExpect(method(HttpMethod.GET))
                .andRespond((response) -> {throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);});
//        Mockito.doThrow(error).when(restTemplate).exchange(
//                eq(url + "friend/record/gru"),
//                eq(HttpMethod.GET),
//                eq(null),
//                any(ParameterizedTypeReference.class));
    }

    public void setUpGetRecordsOk(HttpStatus status) throws Exception {
        RestTemplate restTemplate = mock(RestTemplate.class);
        List<Records> body= new ArrayList<>();
        Records r = new Records();
        r.setSavedCo2Total((float) 4);
        body.add(r);
        friendService.setRestTemplate(restTemplate);
        ResponseEntity<List<Records>> recordz =  new ResponseEntity<>(body, status);
        Mockito.doReturn(recordz).when(restTemplate).exchange(
                eq(url + "friend/record/gru"),
                eq(HttpMethod.GET),
                eq(null),
                any(ParameterizedTypeReference.class));
    }
}