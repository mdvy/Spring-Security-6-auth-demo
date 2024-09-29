package ru.medov.security_demo.config;

import org.springframework.context.annotation.Configuration;
import ru.medov.security_demo.security.JwtTokenFilter;

@Configuration

public class JwtConfig {
    private final JwtTokenFilter jwtTokenFilter;

    public JwtConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }


}
