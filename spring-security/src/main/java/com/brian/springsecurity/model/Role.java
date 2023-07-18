package com.brian.springsecurity.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Table;

@Table(name="authorities")
public enum Role implements GrantedAuthority{
    ROLE_CUSTOMER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
