package com.brian.springsecurity.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/home").setViewName("index.html");
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/api/**").setViewName("greeting.html");
        registry.addViewController("/api/customer").setViewName("greeting.html");
        registry.addViewController("greeting").setViewName("greeting.html");
    }
}
