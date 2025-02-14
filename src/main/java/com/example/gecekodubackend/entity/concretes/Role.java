package com.example.gecekodubackend.entity.concretes;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ADMIN"),
    ROLE_MODERATOR("MODERATOR"),
    ROLE_USER("USER"),
    ROLE_VIP("VIP");


    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

