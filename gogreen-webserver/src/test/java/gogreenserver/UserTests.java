//package gogreenserver;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
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
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import javax.transaction.Transactional;
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
//public class UserTests {
//
//    // for debugging purposes
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
////    private User createDummyUser(String name) {
////        Random rgn = new Random(name.hashCode());
////        return new User(name, "pass" + name, name + "@example.com", "First" + name, "Last" + name,
////            LocalDate.of(1950 + rgn.nextInt(60), rgn.nextInt(13), rgn.nextInt(29)),
////            RandomString.hashOf(name.hashCode()));
////    }
//
//    /**
//     * <ol>
//     * <li>If {@code /api/users/} works.
//     * <li>for each entry in the returned json, if {@code /api/user/<username>}
//     * exists.
//     * <li>Whether {@code /api/user/<username>} returns the same entry as what is
//     * stored in the db.
//     * <li>Whether the amount of entries is correct.
//     * </ol>
//     */
////    @Test
////    public void checkUsers() throws Exception {
////
////        User[] dummyUsers = new User[3];
////        dummyUsers[0] = manager.persist(createDummyUser("Alice"));
////        dummyUsers[1] = manager.persist(createDummyUser("Bob"));
////        dummyUsers[2] = manager.persist(createDummyUser("Charlie"));
////        manager.flush();
////
////        RequestBuilder listReq = MockMvcRequestBuilders.get("/api/users")
////            .accept(MediaType.APPLICATION_JSON);
////        MvcResult res = mockMvc.perform(listReq).andExpect(status().is(200)).andReturn();
////
////        JsonNode list = mapper.readTree(res.getResponse().getContentAsString());
////
////        LOGGER.debug("Returned Json: " + list);
////
////        int usercount = 0;
////        for (JsonNode user : list) {
////            LOGGER.debug("User " + usercount + ": " + user);
////            RequestBuilder userReq = MockMvcRequestBuilders
////                .get("/api/user/" + user.get("username").asText())
////                .accept(MediaType.APPLICATION_JSON);
////            MvcResult ures = mockMvc.perform(userReq).andExpect(status().is(200)).andReturn();
////
////            assertThat(ures.getResponse().getContentAsString())
////                .isEqualTo(mapper.writeValueAsString(dummyUsers[usercount]));
////
////            usercount++;
////        }
////        LOGGER.debug("User amount: " + usercount);
////        assertThat(usercount).isEqualTo(3);
////
////        manager.clear();
////    }
////
////    @Test
////    public void addUser() throws Exception {
////        User dummy = createDummyUser("Danny");
////        RequestBuilder req = MockMvcRequestBuilders.post("/api/user")
////            .contentType(MediaType.APPLICATION_JSON)
////            .content(mapper.writeValueAsString(dummy));
////        mockMvc.perform(req).andExpect(status().is(200));
////
////        assertThat(manager.find(User.class, dummy.getUsername()))
////            .isEqualToComparingFieldByField(dummy);
////
////        manager.clear();
////    }
////
////    @Test
////    public void removeUser() throws Exception {
////        String name = "Ellen";
////        User dummy = createDummyUser(name);
////        manager.persistAndFlush(dummy);
////        RequestBuilder req = MockMvcRequestBuilders.delete("/api/user/" + name);
////        mockMvc.perform(req).andExpect(status().is(200));
////
////        assertThat(manager.find(User.class, dummy.getUsername())).isNull();
////
////        //TODO maybe check if the db is properly empty somehow?
////        manager.clear();
////    }
//}
