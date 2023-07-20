package com.brian.springsecurity.secutiryconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;


@Configuration
@EnableWebSecurity
public class AccessControllByRole {
    
    @Bean
    public HttpSessionRequestCache requestCache() {
        return new HttpSessionRequestCache();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests(auth->auth
                .requestMatchers("/api/customer/**").hasAnyRole("CUSTOMER","ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()            
            )
            .formLogin(withDefaults())
            // .formLogin(
            //     form->form
            //     .usernameParameter("username")
            //     .passwordParameter("password")
            //     .loginPage("/login")
            //     .failureUrl("/login?failed")
            //     .permitAll()
            // )
            .logout(logout->logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
        ;
        return http.build();
    }
}
