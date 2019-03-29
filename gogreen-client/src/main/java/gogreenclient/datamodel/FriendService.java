package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

public class FriendService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExceptionHandler exceptionHandler;

    private String username;
    private String url;


    /**
     * adding friend by userName. That user name will be checked on the server side, whether it's
     * a valid user name, whether it's already your friend. Then corresponding HttpStatus will be
     * returned.
     *
     * @param friendName friend's user name.
     * @return a int value, 1 for successfully adding friend, 0 for friend exists, -1 for adding
     *     failure.
     */
    public int addFriend(String friendName){
        Friend friend = new Friend();
        friend.setAddTime(LocalDateTime.now());
        friend.setFriendName(friendName);
        friend.setUserName(username);
        ResponseEntity<String> response = null;
        try{
           response = restTemplate.postForEntity(url + "/friend", friend, String.class);
        }catch(HttpServerErrorException e){
            if(e instanceof HttpServerErrorException.InternalServerError)
                exceptionHandler.internalServerErrorHandler(e);
        }catch(HttpClientErrorException e){
            if(e instanceof HttpClientErrorException.NotFound)
                exceptionHandler.notFoundHandler(e, friendName);
        }
        if(response != null && response.getStatusCode() == HttpStatus.OK)
            return 1;
        else if(response != null && response.getStatusCode() == HttpStatus.ALREADY_REPORTED)
            return 0;
        else return -1;
    }




    public void setUsername(String username) {
        this.username = username;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
