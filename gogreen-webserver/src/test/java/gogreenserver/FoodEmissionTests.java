//package gogreenserver;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import gogreenserver.entity.FoodEmission;
//import net.bytebuddy.utility.RandomString;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import javax.transaction.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
//
////set up test mvc
//@AutoConfigureWebMvc
//@AutoConfigureMockMvc
//
////set up test db
//@AutoConfigureDataJpa
//@AutoConfigureTestDatabase
//@AutoConfigureTestEntityManager
//@Transactional
//public class FoodEmissionTests {
//
//    private static final Logger LOGGER = LogManager.getLogger("Tests");
//
//    // the ObjectMapper that Spring uses for its object->json conversions
//    @Autowired
//    private ObjectMapper mapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestEntityManager manager;
//
//    @Test
//    public void checkFood() throws Exception {
//        String name = "Apple";
//        FoodEmission dummy = createDummyEmssion(name);
//
//        manager.persistAndFlush(dummy);
//
//        RequestBuilder req = MockMvcRequestBuilders.post("/api/foodEmission")
//            .contentType(MediaType.TEXT_PLAIN).content(name);
//        MvcResult resp = mockMvc.perform(req).andExpect(status().is(200)).andReturn();
//
//        String webresult = resp.getResponse().getContentAsString();
//        String dbresult = mapper
//            .writeValueAsString(manager.find(FoodEmission.class, dummy.getFood()));
//        String actresult = mapper.writeValueAsString(dummy);
//
//        LOGGER.debug("Retrieved value: " + webresult);
//        LOGGER.debug("Stored value: " + dbresult);
//        LOGGER.debug("Actual value: " + actresult);
//
//        assertThat(webresult).isEqualTo(dbresult).isEqualTo(actresult);
//
//        manager.clear();
//    }
//
//    private FoodEmission createDummyEmssion(String name) {
//        return new FoodEmission(name, RandomString.make(), RandomString.make());
//    }
//
//}
