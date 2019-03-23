package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.time.LocalDate;

public class UserModel {


    @Autowired
    private RestTemplate loginRestTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.loginRestTemplate = restTemplate;
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
        user.setEmail(nationality);
        return loginRestTemplate.postForEntity("https://localhost:8443/api/createUser", user,
            User.class);
    }

    public boolean findUser(String username) {
        ResponseEntity<String> response =  loginRestTemplate
            .getForEntity("https://localhost:8443/api/user/findUser/" + username, String.class);
        return response.getBody().equals("success")? true : false;
    }

}
