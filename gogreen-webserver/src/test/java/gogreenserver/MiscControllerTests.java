package gogreenserver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MiscControllerTests {

    private static final Logger LOGGER = LogManager.getLogger("Tests");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void echo() throws Exception {
        // generate random name?
        String name = Integer.toHexString(new Object().hashCode());

        LOGGER.debug("Random string used: " + name);

        RequestBuilder request = MockMvcRequestBuilders.get("/api/greeting/" + name)
            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().is(200))
            .andExpect(content().json("{\"name\":\"" + name + "\"}")).andReturn();
    }
}