package gogreenclient.config;

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


    /**
     * The restTemplateBuilder will be auto injected by Spring.
     *
     * @param builder
     * @return Object restTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
