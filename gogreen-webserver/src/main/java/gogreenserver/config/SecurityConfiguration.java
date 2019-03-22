package gogreenserver.config;

import gogreenserver.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("gogreenserver.security")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    private SimpleUrlAuthenticationFailureHandler myFailureHandleer =
        new SimpleUrlAuthenticationFailureHandler();

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager(); //use default auth manager
    }


    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        //set the user to use bcrypt password encoding.
        auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //disable any crosssite scripting
            .authorizeRequests()
            .antMatchers("/api/createUser").permitAll() //disable auth on api/createUser
            .anyRequest().authenticated() //but enable on everything else
            .and()
            .httpBasic() //when using http built in auth
            .authenticationEntryPoint(restAuthenticationEntryPoint); //always 401
    }
}