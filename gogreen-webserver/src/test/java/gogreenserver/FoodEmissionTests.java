package gogreenserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gogreenserver.entity.FoodEmission;

import net.bytebuddy.utility.RandomString;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class FoodEmissionTests {

    private static final Logger LOGGER = LogManager.getLogger("Tests");

    // the ObjectMapper that Spring uses for its object->json conversions
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager manager;

    @Test
    public void checkFood() throws Exception {
        FoodEmission[] dummyEmmisions = new FoodEmission[3];
        dummyEmmisions[0] = manager.persist(createDummyEmssion("Artichoke"));
        dummyEmmisions[1] = manager.persist(createDummyEmssion("Beans"));
        dummyEmmisions[2] = manager.persist(createDummyEmssion("Cheese"));
        manager.flush();

        RequestBuilder listreq = MockMvcRequestBuilders.get("/api/foodEmission")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult listres = mockMvc.perform(listreq).andExpect(status().is(200)).andReturn();

        JsonNode list = mapper.readTree(listres.getResponse().getContentAsString());

        LOGGER.debug("Returned Json: " + list);

        int counter = 0;
        for (JsonNode career : list) {
            LOGGER.debug("Emission " + counter + ": " + career);
            RequestBuilder req = MockMvcRequestBuilders
                    .get("/api/foodEmission/" + career.get("food").asText())
                    .accept(MediaType.APPLICATION_JSON);
            MvcResult res = mockMvc.perform(req).andExpect(status().is(200)).andReturn();

            assertThat(res.getResponse().getContentAsString())
                    .isEqualTo(mapper.writeValueAsString(dummyEmmisions[counter]));

            counter++;
        }

    }

    @Test
    public void addFood() throws Exception {
        FoodEmission dummy = createDummyEmssion("Drop");
        RequestBuilder req = MockMvcRequestBuilders.post("/api/foodEmission")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy));
        mockMvc.perform(req).andExpect(status().is(200));

        FoodEmission result = manager.find(FoodEmission.class, dummy.getFood());
        LOGGER.debug("Stored value: " + mapper.writeValueAsString(result));
        LOGGER.debug("Dummy value: " + mapper.writeValueAsString(dummy));
        
        assertThat(result).isEqualToComparingFieldByField(dummy);

        manager.clear();
    }

    private FoodEmission createDummyEmssion(String name) {
        return new FoodEmission(name, RandomString.make(), RandomString.make());
    }

}

