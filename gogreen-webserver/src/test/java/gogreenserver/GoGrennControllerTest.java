package gogreenserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GoGreenController.class)
public class GoGrennControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greeting_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
            .get("/api/greeting/zhao")
            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
            .andExpect(status().is(200))
            .andExpect(content().json("{\"name\":\"zhao\"}"))
            .andReturn();
    }
}
