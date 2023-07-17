package com.brian.springsecurity.secutiryconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AuthenticationProviderManagerConfig {

    @Autowired
    private JdbcUserDetailsManager users;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(users);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
    //     auth.authenticationProvider(authenticationProvider());
    // }
}
