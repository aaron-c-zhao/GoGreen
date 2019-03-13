package gogreenserver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gogreenserver.controllers.UserController;
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

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void checkUsers() throws Exception {
    RequestBuilder listReq = MockMvcRequestBuilders.get("/api/users")
        .accept(MediaType.APPLICATION_JSON);
    MvcResult res = mockMvc.perform(listReq).andExpect(status().is(200)).andReturn();

    JsonNode list = (new ObjectMapper()).readTree(res.getResponse().getContentAsString());
    for (JsonNode user : list) {
      RequestBuilder userReq = MockMvcRequestBuilders.get("/api/users/" + user.asText())
          .accept(MediaType.APPLICATION_JSON);
      mockMvc.perform(userReq).andExpect(status().is(200)).andReturn();
    }
  }
}
