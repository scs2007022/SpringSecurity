package com.brian.springsecurity.secutiryconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import com.brian.springsecurity.model.AppUser;
import com.brian.springsecurity.model.Role;

@Configuration
@Component
@EnableWebSecurity
public class DataSourceConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // If there is a UserDetailsService Bean, this inMemory User will not be used.
    // @Bean
    // @ConditionalOnMissingBean(UserDetailsService.class) 
    // InMemoryUserDetailsManager inMemoryUserDetailsManager(){
    //     return new InMemoryUserDetailsManager(
    //         AppUser.builder()
    //             .email("scs2007022@gmail.com")
    //             .name("Brian Lo")
    //             .phone("98765432")
    //             .password(passwordEncoder().encode("P@$$w0rd"))
    //             .role(Role.ROLE_CUSTOMER)
    //             .build()
    //     );
    // }

    // @Value("${spring.datasource.url}")
    // private String url;

    // @Value("${spring.datasource.username}")
    // private String username;

    // @Value("${spring.datasource.password}")
    // private String password;

    // @Bean(name = "postgreDataSource")
    // public DataSource dataSource(){
    //     return DataSourceBuilder.create()
    //         .driverClassName("org.postgresql.Driver")
    //         .url(url)
    //         .username(username)
    //         .password(password)
    //         .build();
    // }

    @Bean
    DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();
    }

    @Bean
    JdbcUserDetailsManager users(){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager();
        users.setDataSource(dataSource());
        users.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");
        users.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username=?");

        AppUser customer = AppUser.builder()
            .username("customer")
            .email("customer@gmail.com")
            .name("Customer")
            .phone("98765432")
            .password(passwordEncoder().encode("P@$$w0rd"))
            .role(Role.ROLE_CUSTOMER)
            .build();
        users.createUser(customer);
        
        AppUser admin = AppUser.builder()
            .username("admin")
            .email("admin@gmail.com")
            .name("Admin")
            .phone("98765432")
            .password(passwordEncoder().encode("P@$$w0rd"))
            .role(Role.ROLE_ADMIN)
            .build();
        users.createUser(admin);

        return users;
    }
}
