package ru.medov.security_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.medov.security_demo.domain.Role;
import ru.medov.security_demo.domain.User;
import ru.medov.security_demo.repository.UserRepository;

@SpringBootApplication
public class SecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);

    }

}
