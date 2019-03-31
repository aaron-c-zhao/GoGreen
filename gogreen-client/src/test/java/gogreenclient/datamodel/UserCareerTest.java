package gogreenclient.datamodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreenclient.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.floatThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserCareerTest {

    @Autowired
    private ObjectMapper objectMapper;
    private RestTemplate template = new RestTemplate();
    private UserCareerService service = new UserCareerService();
    private MockRestServiceServer server;
    private String uri = "https://localhost:8443/api/";


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        server = MockRestServiceServer.createServer(template);
        service.setRestTemplate(template);
        service.setUrl(uri);
    }

    @Test
    public void getCareer_UserExists() throws Exception {
        Records records = new Records();
        records.setUserName("zhao");
        service.setUsername("zhao");
        String userCareer_json = objectMapper.writeValueAsString(records);
        this.setUp_Get(server, "record/" + "zhao", userCareer_json);
        assertEquals(records.getUserName(), service.getCareer().getUserName());
    }


    @Test()
    public void getCareerBadRequest() throws Exception {
        String none = objectMapper.writeValueAsString(null);
        this.setUp_Get(server, "record/" + "zhao", none);
        service.setUsername("zhao");
        String message ="";
        try {
            service.getCareer();
        }
        catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("User career doesn't exist.", message);
    }

    @Test
    public void getAchievementsNotFound() throws Exception {
        notFound("achievement");
        assertEquals(new ArrayList<>(), service.getAchievements());
    }

    @Test
    public void getAchievementsBadRequest() throws Exception {
       badRequest("achievement", null);
        assertEquals(null, service.getAchievements());
    }

    @Test
    public void getAchievementsGoodRequest() throws Exception {
        Achievements ach1 = new Achievements();
        ach1.setUserName("sparrow");
        ach1.setAchieveData(LocalDateTime.of(1, 1, 1, 1, 1, 1, 1));
        Achievements ach2 = new Achievements();
        ach2.setUserName("jack");
        ach2.setAchieveData(LocalDateTime.of(100, 1, 1, 1, 1, 1, 1));
        List<Achievements> list = new ArrayList<>();
        list.add(ach1);
        list.add(ach2);
        String resp = objectMapper.writeValueAsString(list);
        service.setUsername("johnny");
        server.reset();
        setUp_Get(server, "achievement/johnny", resp);
        assertEquals("jack", service.getAchievements().get(1).getUserName());
    }

    @Test
    public void getRecentTwoInsertHistoryNotFound () throws Exception {
        notFound("insertHistory");
        assertEquals(new ArrayList<>(), service.getRecentTwoInsertHistory());
    }

    @Test
    public void getRecentTwoInsertHistoryBadRequest() throws Exception{
        List<InsertHistoryCo2> list = new ArrayList<>();
        list.add(new InsertHistoryCo2());
        badRequest("insertHistory", list);
        assertEquals(null, service.getRecentTwoInsertHistory());
    }

    @Test
    public void getRecentTwoInsertHistoryNull() throws Exception{
        badRequest("insertHistory", null);
        assertEquals(null, service.getRecentTwoInsertHistory());
    }

    @Test
    public void getRecentTwoInsertHistoryGoodRequest() throws Exception {
        InsertHistoryCo2 hist1 = new InsertHistoryCo2();
        hist1.setActivityName("chilling");
        List<InsertHistoryCo2> list = new ArrayList<>();
        list.add(hist1);
        String resp = objectMapper.writeValueAsString(list);
        service.setUsername("jackie");
        setUp_Get(server, "insertHistory/jackie", resp);
        assertEquals("chilling", service.getRecentTwoInsertHistory().get(0).getActivityName());
    }

    @Test
    public void getActivityAmountNotFound() throws Exception {
        notFound("insertHistory/amount");
        assertEquals("0", service.getActivityAmount());
    }

    @Test
    public void getActivityAmountBadRequst() throws Exception {
        badRequest("insertHistory/amount", "69");
        assertEquals(null, service.getActivityAmount());
    }

    @Test
    public void getActivityAmountNull() throws Exception {
        badRequest("insertHistory/amount", null);
        assertEquals(null, service.getActivityAmount());
    }

    @Test
    public void getActivityAmountGoodRequest() throws Exception {
        service.setUsername("pablo");
        setUp_Get(server, "insertHistory/amount/pablo", "24");
        assertTrue(service.getActivityAmount().equals("24"));
    }

    @Test
    public void getActiveDaysNotFound() throws Exception {
        notFound("insertHistory/days");
        assertEquals("0", service.getActiveDays());
    }

    @Test
    public void getActiveDaysBadRequest() throws Exception {
        badRequest("insertHistory/days", "4");
        assertEquals(null, service.getActiveDays());
    }

    @Test
    public void getActiveDaysNull() throws Exception {
        badRequest("insertHistory/days", null);
        assertEquals(null, service.getActiveDays());
    }

    @Test
    public void getActiveDaysGoodRequest() throws Exception {
        service.setUsername("pablo");
        setUp_Get(server, "insertHistory/days/pablo", "27");
        assertTrue(service.getActiveDays().equals("27"));
    }

    public void badRequest(String urii, Object obj) throws Exception {
        String resp = objectMapper.writeValueAsString(obj);
        service.setUsername("johnny");
        server.reset();
        server.expect(requestTo(uri + urii + "/johnny"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.BAD_REQUEST).body(resp));
    }

    public void notFound(String urii) throws Exception {
        String resp = objectMapper.writeValueAsString(null);
        service.setUsername("johnny");
        server.reset();
        server.expect(requestTo(uri + urii + "/johnny"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
    }

    public void setUp_Post(MockRestServiceServer server, String uri, String content, String json) throws Exception {
        server.reset();
        server.expect(requestTo(uri))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string(content))
            .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
    }

    public void setUp_Get(MockRestServiceServer server, String urii, String json) throws Exception {
        server.reset();
        server.expect(requestTo(uri + urii))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
    }

}