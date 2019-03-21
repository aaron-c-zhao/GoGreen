package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * A service class which will provide the service of retrieving user career from database,
 * updating user career in the database.
 */

public class UserCareerService {

    private final String url;
    @Autowired
    private RestTemplate restTemplate;
    private String username;

    public UserCareerService() {
        this.url = "https://localhost:8443/api/career";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get userCareer from database. If there is no usrCareer existing in the database,
     * then a new userCareer tuple will be created with the username logged in.
     *
     * @return userCareer of the current user who has logged in.
     * @throws Exception threw by restTemplate.
     */
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

    /**
     * Create a new user career tuple in the database.
     *
     * @return HttpResponseEntity of UserCareer.
     */
    public ResponseEntity<UserCareer> createUserCareer() {
        UserCareer career = new UserCareer();
        career.setUsername(username);
        career.setCo2saved(0);
        ResponseEntity<UserCareer> response = restTemplate
            .postForEntity(url, career, UserCareer.class);
        return response;
    }

    /**
     * Update one's career, first it will retrieve the current status of this user's career,
     * then it will update the amount of CO2 saved by this user.
     *
     * @param changedCO2 the amount saved by user's activity.
     * @return the updated userCareer
     * @throws Exception threw by restTemplate.
     */
    public UserCareer updateUserCareer(int changedCO2) throws Exception {
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

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
