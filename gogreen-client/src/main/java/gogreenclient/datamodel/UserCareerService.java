package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

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
        this.url = "https://localhost:8443/api";
    }

    /**
     * Get userCareer from database. If there is no usrCareer existing in the database,
     * then a new userCareer tuple will be created with the username logged in.
     *
     * @return userCareer of the current user who has logged in.
     * @throws Exception threw by restTemplate.
     */
    public Records getCareer() {
        Records records = restTemplate.getForObject(url + "/record/" + username, Records.class);
        if (records == null) {
            throw new RuntimeException("User career doesn't exist.");
        } else {
            return records;
        }
    }

    /**
     * Get achievements of this user.
     *
     * @return list of achievements.
     */
    public List<Achievements> getAchievements() {
        ResponseEntity<List<Achievements>> response = restTemplate.exchange(
            url + "/achievement/" + username,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Achievements>>() {
            }
        );
        List<Achievements> achievements = null;
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            achievements = response.getBody();
            achievements.sort(Comparator.comparing(Achievements::getAchieveData));
        }
        return achievements;
    }

    /**
     * Get the most recent two insert history of this user.
     * @return list of insert history.
     */
    public List<InsertHistoryCo2> getRecentTwoInsertHistory() {
        ResponseEntity<List<InsertHistoryCo2>> response = restTemplate.exchange(
            url + "/insertHistory/" + username,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<InsertHistoryCo2>>() {
            }
        );
        List<InsertHistoryCo2> insertHistories = null;
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            insertHistories = response.getBody();
        }
        return insertHistories;
    }

    /**
     * Get the amount of this user's activities.
     *
     * @return the string representation of the number of activities.
     */
    public String getActivityAmount() {
        ResponseEntity<String> response = restTemplate
            .getForEntity(url + "/insertHistory/amount/" + username,
                String.class);
        String amount = null;
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            amount = response.getBody();
        }
        return amount;
    }

    /**
     * Get the active days of this user.
     *
     * @return the string representation of the number of days.
     */
    public String getActiveDays() {
        ResponseEntity<String> response = restTemplate
            .getForEntity(url + "/insertHistory/days/" + username,
                String.class);
        String days = "";
        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            days = response.getBody();
        }
        return days;
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
