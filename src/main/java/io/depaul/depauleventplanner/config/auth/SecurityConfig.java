package io.depaul.depauleventplanner.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * --- /login
     * --- /events
     * --- /events/available-rooms/register                     : new event with available room
     * --- /events/{eventId}                                    : view an event (registered)
     * --- /events/{eventId}/reserve                            : reserve spot at event
     * --- /events/{eventId}/reserve/cancel                     : cancel spot at event
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/css/*", "/js/*", "/templates/*", "/static/*", "/images/*").permitAll()
                .antMatchers("/events", "/events/*", "/home").authenticated()
                .and().formLogin()
                .and().httpBasic();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }



}
