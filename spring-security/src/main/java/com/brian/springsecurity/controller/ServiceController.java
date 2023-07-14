package com.brian.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServiceController {
    
    @GetMapping
    public String greeting(){
        return "Everyone can access this, no authentication is required.";
    }
    @GetMapping("/customer")
    public String customer(){
        return "Only authenticated customer and admin can read this.";
    }
    @GetMapping("/admin")
    public String admin(){
        return "Only authenticated admin can read this.";
    }
}
