package ru.medov.security_demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.medov.security_demo.domain.Role;
import ru.medov.security_demo.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private Role role;
}
