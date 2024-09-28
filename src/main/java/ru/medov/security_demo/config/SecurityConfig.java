package ru.medov.security_demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.medov.security_demo.domain.Role;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(auth->auth
                                .requestMatchers( "/welcome").permitAll()
                                .requestMatchers(GET, "/api/v1/users/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(POST, "/api/v1/users/**").hasAnyRole("ADMIN")
                                .requestMatchers(DELETE, "/api/v1/users/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated())
                        //.formLogin(Customizer.withDefaults())
                        .httpBasic(Customizer.withDefaults())
                        .logout(Customizer.withDefaults())
                        .build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .roles("USER")
                        .build()

        );
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }




}
