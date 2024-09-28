package ru.medov.security_demo.domain;


public enum Role {
    ROLE_USER, ROLE_ADMIN;



    public String getName() {
        return this.name().substring(5);
    }
}
