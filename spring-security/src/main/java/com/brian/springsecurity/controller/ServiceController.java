package com.brian.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brian.springsecurity.model.AppUser;
import com.brian.springsecurity.model.Role;
import com.brian.springsecurity.service.AppUserDetailsService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/")
public class ServiceController {
    
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @GetMapping("/public")
    public String greeting(){
        return "greeting";
    }
    
    @GetMapping("/customer")
    public String customer(Model model){
        AppUser appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name",appUser.getName());
        if (appUser.getRoles().contains(Role.ROLE_CUSTOMER)){
            model.addAttribute("isCustomer");
        }
        log.debug("Called /api/customer, the principal.getName()[{}]",appUser.getUsername());
        return "greeting";
    }
    
    @GetMapping("/admin")
    public String admin(Model model){
        AppUser appUser = (AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name",appUser.getName());
        if (appUser.getRoles().contains(Role.ROLE_ADMIN)){
            model.addAttribute("isAdmin");
        }
        log.debug("Called /api/admin, the principal.getName()[{}]",appUser.getUsername());
        return "greeting";
    }

    @PostMapping("/register")
    public String register(@RequestBody AppUser appUser,Model model){
        boolean success = appUserDetailsService.register(appUser);
        if (success){
            log.info("The account {} is created.", appUser);
            model.addAttribute("register","success");
        }
        else{
            log.info("The account {} is not created.", appUser);
            model.addAttribute("register", "unsuccess");
            return "register";
        }
        return "login";
    }
    
    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
