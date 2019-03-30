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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class FoodActivityModelTest {

    @Autowired
    private ObjectMapper objectMapper;
    private FoodEmissionModel model = new FoodEmissionModel();
    private RestTemplate template;
    private MockRestServiceServer server;
    private String uri;
    private FoodActivity yummy = new FoodActivity();
    private FoodActivity yum = new FoodActivity();
    private FoodActivity yummy_and_healthy = new FoodActivity();

    @Before
    public void setUp() throws Exception {
        template = new RestTemplate();
        uri = "https://localhost:8443/api/foodEmission";
        model.setRestTemplate(template);
        MockRestServiceServer.MockRestServiceServerBuilder builder = MockRestServiceServer.bindTo(template);
        builder.ignoreExpectOrder(true);
        server = builder.build();
        String food = "apple";
        yummy.setFood(food);
        String yummy_json = objectMapper.writeValueAsString(yummy);
        server.expect(requestTo(uri))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string(food))
            .andRespond(withSuccess(yummy_json, MediaType.APPLICATION_JSON));
    }

    @Test
    public void getFoodEmissions() {
        assertEquals(yummy.getFood(), model.getFoodEmissions("apple").getFood());
    }

    @Test
    public void compareFood_same() {
        String use = "nuts";
        int res = model.compareFood(use, use, 1, 1);
        assertEquals(0, res);
    }

    @Test
    public void compareFood_saved() throws Exception {
        assertEquals(5, setUp(5, 10, "apple", "nuts", 1));
    }

    @Test
    public void compareFood_lost() throws Exception {
        assertEquals(-5, setUp(5, 5, "apple", "apple", 2));
    }


    public int setUp(int f1, int f2, String food1, String food2, int food1Quantity) throws Exception {
        yum.setFood(food1);
        yum.setEmission(f1);
        String yum_json = objectMapper.writeValueAsString(yum);
        yummy_and_healthy.setFood(food2);
        yummy_and_healthy.setEmission(f2);
        String yumhealth_json = objectMapper.writeValueAsString(yummy_and_healthy);
        server.reset();
        this.setUp_Post(server, uri, food1, yum_json);
        this.setUp_Post(server, uri, food2, yumhealth_json);
        int res = model.compareFood(food1, food2, food1Quantity, 1);
        return res;
    }

    public void setUp_Post(MockRestServiceServer server, String uri, String content, String json) throws Exception {
        server.expect(requestTo(uri))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().string(content))
            .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
    }

    public void setUp_Get(MockRestServiceServer server, String uri, String json) throws Exception {
        server.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
    }
}