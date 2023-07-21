package com.brian.springsecurity.secutiryconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
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
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/customer/**").hasAnyRole("CUSTOMER","ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()           
            )
            // .formLogin(withDefaults())
            .formLogin(
                form->form
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout->logout
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .csrf(csrf->csrf.disable())
            .headers(header->header.frameOptions(frame->frame.disable()))
        ;
        return http.build();
    }
}
