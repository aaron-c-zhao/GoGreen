package gogreenclient.config;

import gogreenclient.datamodel.UserModel;
import gogreenclient.screens.ScreenConfiguration;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;


@Configuration
@Import( {ScreenConfiguration.class})
@EnableAutoConfiguration
@PropertySource("classpath:application.yml")
public class AppConfig {

    @Value("classpath:clienttrust.p12")
    private Resource trustStore;
    @Value("group82")
    private String trustStorePassword;

    @Bean
    UserModel userModel() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setRestTemplate(restTemplate());
        return userModel;
    }

    /**
     * The restTemplateBuilder will be auto injected by Spring.
     *
     * @return Object restTemplate
     */
    @Bean
    public RestTemplate restTemplate() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
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


}
