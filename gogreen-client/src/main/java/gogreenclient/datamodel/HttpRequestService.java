package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


public class HttpRequestService {

    @Autowired
    private RestTemplate restTemplate;

    protected ResponseEntity<User> postRequest(User myRequestBody, URI uri, MediaType mediaType) {
        RequestEntity<User> request = RequestEntity
            .post(uri)
            .accept(mediaType)
            .body(myRequestBody);
        ResponseEntity<User> reponse = restTemplate.exchange(request, User.class);
        return reponse;
    }

    protected ResponseEntity<UserCareer> postRequest(UserCareer myRequestBody,
                                                     URI uri, MediaType mediaType){
        RequestEntity<UserCareer> request = RequestEntity
            .post(uri)
            .accept(mediaType)
            .body(myRequestBody);
        ResponseEntity<UserCareer> response = restTemplate.exchange(request, UserCareer.class);
        return response;
    }


    protected ResponseEntity<String> getReuest(URI uri, MediaType mediaType) {
        RequestEntity<Void> request = RequestEntity
            .get(uri)
            .accept(mediaType)
            .build();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response;
    }
}
