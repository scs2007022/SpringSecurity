package com.brian.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brian.springsecurity.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
    Optional<Customer> findByEmail(String email);

}
