package gogreenclient.config;

import gogreenclient.datamodel.FoodEmissionModel;
import gogreenclient.datamodel.HttpRequestService;
import gogreenclient.datamodel.UserModel;
import gogreenclient.screens.ScreenConfiguration;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;


@Configuration
@Import( {ScreenConfiguration.class})
@EnableAutoConfiguration
public class AppConfig {

    @Value("classpath:truststore.jks")
    private Resource trustStore;
    @Value("group82")
    private String trustStorePassword;
    @Value("classpath:identity.jks")
    private Resource keyStore;
    @Value("group82")
    private String keyStorePassword;

    @Bean
    UserModel userModel() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setHttpRequestService(httpRequestService());
        return userModel;
    }

    /**
     * A Bean that spring will hold and can be instantiated anywhere.This is the
     * right way to use spring and dataModel.
     *
     * @return an instance of FoodEmissionModel.
     * @throws Exception
     */
    @Bean
    FoodEmissionModel foodEmissionModel() throws Exception {
        FoodEmissionModel foodEmissionModel = new FoodEmissionModel();
        foodEmissionModel.setRestTemplate(restTemplate());
        return foodEmissionModel;
    }

    /**
     * The restTemplateBuilder will be auto injected by Spring.
     *
     * @return Object restTemplate
     */
    @Bean
    public RestTemplate restTemplate() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keyStore.getFile()), keyStorePassword.toCharArray());
        SSLContext sslContext = new SSLContextBuilder()
            .loadKeyMaterial(ks, keyStorePassword.toCharArray())
            .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
            .build();
        SSLConnectionSocketFactory socketFactory =
            new SSLConnectionSocketFactory(sslContext);
        HttpClient httpClient = HttpClients.custom()
            .setSSLSocketFactory(socketFactory).build();
        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }

    @Bean
    public HttpRequestService httpRequestService() {
        return new HttpRequestService();
    }



}
