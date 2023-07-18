package com.brian.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brian.springsecurity.model.AppUser;
import com.brian.springsecurity.repository.AppuserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

    @Autowired
    private AppuserRepository appuserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appuserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("AppUser not found."));
        return appUser;
    }
    
}
