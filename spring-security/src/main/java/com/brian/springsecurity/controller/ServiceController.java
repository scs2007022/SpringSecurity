package com.brian.springsecurity.controller;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

// @RestController
@Log4j2
@RequestMapping("/api")
public class ServiceController {
    
    @GetMapping("/public")
    public String greeting(){
        return "greeting";
    }
    @GetMapping("/customer")
    public String customer(Model model,Principal principal){
        model.addAttribute("username",principal.getName());
        log.debug("Testing"+principal.getName());
        return "greeting";
    }
    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        model.addAttribute("username", principal.getName());
        return "greeting";
    }
}
