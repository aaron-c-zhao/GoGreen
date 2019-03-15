package gogreenclient.config;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    @Lazy
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
    @Lazy
    public RestTemplate restTemplate() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keyStore.getFile()), keyStorePassword.toCharArray());
        SSLContext sslContext = new SSLContextBuilder()
            .loadKeyMaterial(ks, keyStorePassword.toCharArray())
            .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
            .build();
        SSLConnectionSocketFactory socketFactory =
            new SSLConnectionSocketFactory(sslContext);
//        CredentialsProvider provider =
//            new BasicCredentialsProvider();
//        UsernamePasswordCredentials credentials =
//            new UsernamePasswordCredentials(username, password);
//        provider.setCredentials(AuthScope.ANY, credentials);
        HttpClient httpClient = HttpClients.custom()
            .setSSLSocketFactory(socketFactory)
//            .setDefaultCredentialsProvider(provider)
            .build();
        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate= restTemplateBuilder
            .basicAuthentication(username, password).build();
        restTemplate.setRequestFactory(factory);
        return restTemplate;

    }


}
