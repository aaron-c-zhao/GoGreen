package gogreenclient.config;

import gogreenclient.datamodel.UserModel;
import gogreenclient.screens.ScreenConfiguration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(ScreenConfiguration.class)
@EnableAutoConfiguration
@ImportResource("classpath:applicationContext-security.xml")
public class AppConfig {


    @Bean
    UserModel userModel() {
        UserModel userModel = new UserModel();
        userModel.setRestTemplate(restTemplate(new RestTemplateBuilder()));
        return userModel;
    }

    /**
     * The restTemplateBuilder will be auto injected by Spring.
     *
     * @param builder a standard restTemplateBuilder
     * @return Object restTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}
