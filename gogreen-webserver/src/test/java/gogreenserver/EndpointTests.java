package gogreenserver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gogreenserver.controllers.MiscController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(MiscController.class)
public class EndpointTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void echo() throws Exception {
        // generate random name?
        String name = Integer.toHexString(new Object().hashCode());

        RequestBuilder request = MockMvcRequestBuilders.get("/api/greeting/" + name)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().is(200))
                .andExpect(content().json("{\"name\":\"" + name + "\"}")).andReturn();
    }

    @Test
    public void checkUsers() throws Exception {

        RequestBuilder listReq = MockMvcRequestBuilders.get("/api/users/")
                .accept(MediaType.APPLICATION_JSON);

        var res = mockMvc.perform(listReq).andExpect(status().is(200)).andReturn();
        JsonNode list = (new ObjectMapper()).readTree(res.getResponse().getContentAsString());
        for (JsonNode user : list) {
            RequestBuilder userReq = MockMvcRequestBuilders.get("/api/users/" + user.asText())
                    .accept(MediaType.APPLICATION_JSON);
            mockMvc.perform(userReq).andExpect(status().is(200)).andReturn();
        }
    }
}
