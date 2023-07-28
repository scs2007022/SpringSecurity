package com.brian.springsecurity.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brian.springsecurity.model.AppUser;
import com.brian.springsecurity.model.Role;
import com.brian.springsecurity.repository.AppuserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AppUserDetailsService implements UserDetailsService{

    @Autowired
    private AppuserRepository appuserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(AppUser appUser){
        if(appuserRepository.findByUsername(appUser.getUsername()).isPresent()){
            log.info("The username has been taken.");
            return false;
        }
        else{
            Set<Role> r = new HashSet<>();
            r.add(Role.ROLE_CUSTOMER);
            appUser.setRoles(r);
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appuserRepository.save(appUser);
            return true;
        }
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appuserRepository
            .findByUsername(username)
            .orElseThrow(()->new UsernameNotFoundException("AppUser not found with username: "+username))
        ;
        return appUser;
    }
    
}
