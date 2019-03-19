package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * A service class which will provide the service of retrieving user career from database,
 * updating user career in the database.
 */

public class UserCareerService {

    @Autowired
    private RestTemplate restTemplate;

    private String username;

    final private String url;

    public UserCareerService() {
        this.url = "https://localhost:8443/api/career";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCareer getCareer() throws Exception {
        UserCareer userCareer = restTemplate.getForObject(url + "/" + username, UserCareer.class);
        ResponseEntity<UserCareer> response = null;
        if (userCareer == null) {
            response = createUserCareer();
            if (response != null && response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new Exception("Bad request for creating user career.");
            }
        } else {
            return userCareer;
        }
    }

    public ResponseEntity<UserCareer> createUserCareer() throws Exception{
        UserCareer career = new UserCareer();
        career.setUsername(username);
        career.setCo2saved(0);
        ResponseEntity<UserCareer> response = restTemplate
            .postForEntity(url, career, UserCareer.class);
        return response;
    }

    public UserCareer updateUserCareer(int changedCO2) throws Exception{
        UserCareer userCareer = getCareer();
        UserCareer finalCareer = null;
        userCareer.setCo2saved(userCareer.getCo2saved() + changedCO2);
        ResponseEntity<UserCareer> response = restTemplate
            .postForEntity(url + "update", userCareer, UserCareer.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            finalCareer = userCareer;
        } else {
            throw new Exception("Bad request for updating user career.");
        }
        return finalCareer;
    }


}
