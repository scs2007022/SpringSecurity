// package com.brian.springsecurity.secutiryconfig;

// import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
// import org.springframework.context.ApplicationEventPublisher;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationEventPublisher;
// import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// @Configuration
// @EnableWebSecurity
// public class AuthEventPublisher {

//     @Bean
//     @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
//     DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate){
//         return new DefaultAuthenticationEventPublisher(delegate);
//     }
// }
