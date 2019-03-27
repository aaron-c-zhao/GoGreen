package gogreenserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gogreenserver.entity.InsertHistory;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
public class InsertHistoryTests {

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

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    /**
     * Check if /api/insertHistories/ and /api/insertHistory/{username} works.
     */
    @WithMockUser
    @Test
    public void checkInsertHistories() throws Exception {

        LOGGER.debug("=== checkInsertHistories() ===");
        InsertHistory[] dummies = new InsertHistory[3];
        dummies[0] = manager.persist(createDummyInsertHistory(0, "Alexander"));
        dummies[1] = manager.persist(createDummyInsertHistory(1, "Bonaparte"));
        dummies[2] = manager.persist(createDummyInsertHistory(2, "Charlemagne"));
        manager.flush();

        RequestBuilder ereq = MockMvcRequestBuilders.get("/api/insertHistories")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult eres = mockMvc.perform(ereq).andExpect(status().is(200)).andReturn();

        JsonNode list = mapper.readTree(eres.getResponse().getContentAsString());

        LOGGER.debug("Returned Json: " + list);

        int achcount = 0;
        for (JsonNode ach : list) {
            LOGGER.debug("History record record " + achcount + ": " + ach);
            RequestBuilder achreq = MockMvcRequestBuilders
                    .get("/api/insertHistory/" + ach.get("userName").asText())
                    .accept(MediaType.APPLICATION_JSON);
            MvcResult ures = mockMvc.perform(achreq).andExpect(status().is(200)).andReturn();

            // the brackets [] are there because we get a list.
            assertThat(ures.getResponse().getContentAsString())
                    .isEqualTo('[' + mapper.writeValueAsString(dummies[achcount]) + ']');

            achcount++;
        }
        LOGGER.debug("History record amount: " + achcount);
        assertThat(achcount).isEqualTo(3);

        manager.clear();

    }

    @WithMockUser
    @Test
    public void checkNonexistentHistories() throws Exception {

        LOGGER.debug("=== checkNonexistentHistories() ===");

        RequestBuilder req = MockMvcRequestBuilders.get("/api/insertHistory/nobody")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(req).andExpect(status().is(404)).andReturn();
    }

    @WithMockUser
    @Test
    public void addHistory() throws Exception {

        LOGGER.debug("=== addHistory() ===");

        InsertHistory dummy = createDummyInsertHistory(0, "Attila");
        RequestBuilder req = MockMvcRequestBuilders.post("/api/insertHistory")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dummy))
                .header("userName", dummy.getUserName());
        mockMvc.perform(req).andExpect(status().is(200));

        InsertHistory found = manager.find(InsertHistory.class, 0);
        assertThat(found).isEqualToComparingFieldByField(dummy);

        manager.clear();
    }

    private InsertHistory createDummyInsertHistory(int id, String name) {
        InsertHistory history = new InsertHistory();
        history.setUserName(name);
        // this should not be done in the real db, but our mock does not auto generate
        // the id.
        history.setInsertId(id);
        return history;
    }
}
