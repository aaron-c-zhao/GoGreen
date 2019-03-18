package gogreenclient.datamodel;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

public class UserModel {

    private HttpRequestService httpRequestService;

    public void setHttpRequestService(HttpRequestService httpRequestService) {
        this.httpRequestService = httpRequestService;
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
        return httpRequestService.postRequest(user, new URI("https://localhost:8443/api/user"),
            MediaType.APPLICATION_JSON);
    }

    public ResponseEntity<String> login() throws URISyntaxException {
        return httpRequestService.getRequest(new URI("https://localhost:8443/api/login"),
            MediaType.APPLICATION_JSON);
    }

}
