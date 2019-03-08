package gogreenclient.dataModel;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;

public class UserModel {

    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<User> addUser(String username, String password, LocalDate bdate, String nationality) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setBdate(bdate);
        user.setNationality(nationality);
        return postRequest(user, new URI("http://localhost:8082/api/user"), MediaType.APPLICATION_JSON);
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
