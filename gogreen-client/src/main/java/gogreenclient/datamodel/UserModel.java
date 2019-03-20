package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.time.LocalDate;

public class UserModel {


    @Autowired
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Method that deals with the communication between server and client.
     *
     * @param username    the username retrived from the GUI, should be a string
     * @param password    the password retrived from the GUI, should be a string
     * @param bdate       the birthday of the user, should be of type localdate
     * @param nationality the nationality of the user, retrived from the GUI, should be a string
     * @return this method will return a responseEntity
     * @throws Exception throw IOException
     */
    @SuppressWarnings("unchecked")
    public ResponseEntity<User> addUser(String username, String password, LocalDate bdate,
                                        String nationality) throws URISyntaxException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setBdate(bdate);
        user.setNationality(nationality);
        return restTemplate.postForEntity("https://localhost:8443/api/user", user,
            User.class);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
