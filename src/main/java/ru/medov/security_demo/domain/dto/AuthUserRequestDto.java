package ru.medov.security_demo.domain.dto;

import lombok.Data;

@Data
public class AuthUserRequestDto {
    private String username;
    private String password;
}
