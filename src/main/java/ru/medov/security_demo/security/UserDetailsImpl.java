package ru.medov.security_demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.medov.security_demo.domain.User;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    private UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public static UserDetailsImpl fromUser(User user){
        return new UserDetailsImpl(user);
    }
}
