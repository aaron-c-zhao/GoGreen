package gogreenclient.datamodel;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserModel {

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
    public ResponseEntity<User> addUser(String username, String password, LocalDate bdate,
                                        String nationality) throws URISyntaxException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setBdate(bdate);
        user.setNationality(nationality);
        return postRequest(user, new URI("http://localhost:8082/api/user"),
            MediaType.APPLICATION_JSON);
    }

    public boolean userLogin() {
        return true;
    }

    private ResponseEntity<User> postRequest(User myRequestBody, URI uri, MediaType mediaType) {
        RequestEntity<User> request = RequestEntity
            .post(uri)
            .accept(mediaType)
            .body(myRequestBody);
        ResponseEntity<User> reponse = restTemplate.exchange(request, User.class);
        return reponse;
    }

}
