package com.brian.springsecurity.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brian.springsecurity.config.JwtService;
import com.brian.springsecurity.model.Customer;
import com.brian.springsecurity.model.Role;
import com.brian.springsecurity.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var customer = Customer.builder()
            .firstName(request.getFirstname())
            .lastName(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        customerRepository.save(customer);
        var jwtToken = jwtService.generateJwt(customer);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())));
        var customer = customerRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateJwt(customer);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
