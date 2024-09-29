package ru.medov.security_demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.medov.security_demo.domain.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {
    private String username;
    private String password;
    private Role role;
}
