package com.example.gecekodubackend.entity.concretes;

public enum Role implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String getAuthority(){
        return name();
    }
}
