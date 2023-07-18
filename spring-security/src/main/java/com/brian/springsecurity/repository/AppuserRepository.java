package com.brian.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brian.springsecurity.model.AppUser;

public interface AppuserRepository extends JpaRepository<AppUser,Long>{
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
}
