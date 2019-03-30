package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.time.LocalDate;

public class UserService {


    @Autowired
    private RestTemplate loginRestTemplate;

    private String url;


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
        return loginRestTemplate.postForEntity(url + "/createUser", user,
            User.class);
    }

    /**
     * Find if a user exists in the DB. None of user's detail will be received,
     * so this method is save to use
     *
     * @param username username entered by user while creating account.
     * @return true - the username is already used, false if it is valid.
     */
    public boolean findUser(String username) {
        try {
            ResponseEntity<String> response = loginRestTemplate
                .getForEntity(url + "/user/findUser/" + username, String.class);
        } catch (HttpClientErrorException e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                return false;
            }
        }
        return true;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
