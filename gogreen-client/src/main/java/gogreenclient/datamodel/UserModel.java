package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.time.LocalDate;

public class UserModel {


    @Autowired
    private RestTemplate loginRestTemplate;


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

    /**
     * Find if a user exists in the DB. None of user's detail will be received,
     * so this method is save to use
     *
     * @param username username entered by user while creating account.
     * @return true - the username is already used, false if it is valid.
     */
    public boolean findUser(String username) {
        ResponseEntity<String> response = loginRestTemplate
            .getForEntity("https://localhost:8443/api/user/findUser/" + username, String.class);
        return response.getBody().equals("success") ? true : false;
    }

    public ResponseEntity<String> uploadPhoto(File file) {
        loginRestTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap body = new LinkedMultiValueMap();
        body.add("profile_pic", new FileSystemResource(file));
        HttpEntity request = new HttpEntity<>(body, headers);
        return loginRestTemplate.postForEntity("https://localhost:8443/api/createUser/upload", request, String.class);
    }

}
