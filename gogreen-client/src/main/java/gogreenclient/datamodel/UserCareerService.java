package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
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
        this.url = "https://localhost:8443/api/record";
    }

    /**
     * Get userCareer from database. If there is no usrCareer existing in the database,
     * then a new userCareer tuple will be created with the username logged in.
     *
     * @return userCareer of the current user who has logged in.
     * @throws Exception threw by restTemplate.
     */
    public Records getCareer() {
        Records records = restTemplate.getForObject(url + "/" + username, Records.class);
        if (records == null) {
            throw new RuntimeException("User career doesn't exist.");
        } else {
            return records;
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
