package com.brian.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.brian.springsecurity.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    
    private final CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> customerRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found."));
    }
}
