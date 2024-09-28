package ru.medov.security_demo.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role{
    ROLE_USER(Set.of(Permission.USER_READ)), ROLE_ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE));

    private Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getAuthorities(){
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
