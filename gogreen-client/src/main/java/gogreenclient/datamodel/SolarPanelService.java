package gogreenclient.datamodel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class SolarPanelService {

    private AddSolarpanels addSolarpanels;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExceptionHandler exceptionHandler;

    private String url;

    private String userName;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Read the current size of this user's solar panel.
     *
     * @return the current size of this user's solar panel. default value is 0.
     */
    public float getSolarPanelSize() {
        ResponseEntity<AddSolarpanels> response = null;
        try {
            response = restTemplate
                .getForEntity(url + "/addSolarpanel/" + userName, AddSolarpanels.class);
        } catch (HttpClientErrorException e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                return 0;
            }
        }
        addSolarpanels = response.getBody();
        return addSolarpanels.getArea();

    }
}
