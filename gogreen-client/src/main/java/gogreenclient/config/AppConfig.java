package gogreenclient.config;

import gogreenclient.datamodel.FoodEmissionModel;
import gogreenclient.datamodel.UserCareerService;
import gogreenclient.datamodel.UserModel;
import gogreenclient.screens.ScreenConfiguration;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;


/**
 * Configuration of all the client side spring elements, namely Beans. These Beans will be
 * instantiated in a lazy manner, because some of the fields needed to be initialized by
 * user's input, and some Beans need those values to acting normally.
 */
@Configuration
@ComponentScan("gogreenclient.datamodel")
@Import( {ScreenConfiguration.class})
@EnableAutoConfiguration
@Lazy
public class AppConfig {

    @Value("classpath:truststore.jks")
    private Resource trustStore;
    @Value("group82")
    private String trustStorePassword;
    @Value("classpath:identity.jks")
    private Resource keyStore;
    @Value("group82")
    private String keyStorePassword;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;


    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    UserModel userModel() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setRestTemplate(restTemplate());
        return userModel;
    }

    /**
     * A Bean that spring will hold and can be instantiated anywhere.This is the
     * right way to use spring and dataModel.
     *
     * @return an instance of FoodEmissionModel.
     * @throws Exception normal exception.
     */
    @Bean
    FoodEmissionModel foodEmissionModel() throws Exception {
        FoodEmissionModel foodEmissionModel = new FoodEmissionModel();
        foodEmissionModel.setRestTemplate(restTemplate());
        return foodEmissionModel;
    }


    @Bean
    UserCareerService userCareerService() {
        UserCareerService userCareerService = new UserCareerService();
        userCareerService.setUsername(username);
        return userCareerService;
    }

    /**
     * The restTemplateBuilder will be auto injected by Spring.
     *
     * @return Object restTemplate
     */
    @Bean
    @Scope("prototype")
    public RestTemplate loginRestTemplate() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keyStore.getFile()), keyStorePassword.toCharArray());
        SSLContext sslContext = new SSLContextBuilder()
            .loadKeyMaterial(ks, keyStorePassword.toCharArray())
            .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
            .build();
        SSLConnectionSocketFactory socketFactory =
            new SSLConnectionSocketFactory(sslContext);
        HttpClient httpClient = HttpClients.custom()
            .setSSLSocketFactory(socketFactory)
            .build();
        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate;
        if (username != null && password != null) {
            restTemplate = restTemplateBuilder
                .basicAuthentication(username, password).build();
        } else {
            restTemplate = restTemplateBuilder.build();
        }
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    /**
     * This Bean will provide the final resTemplate which contains all the information about
     * TLS context and authentication.
     *
     * @return restTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return restTemplate;
    }


}
