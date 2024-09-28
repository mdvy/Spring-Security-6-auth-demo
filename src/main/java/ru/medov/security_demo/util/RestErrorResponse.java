package ru.medov.security_demo.util;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestErrorResponse {

    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public RestErrorResponse(String message) {
        this.message = message;
    }
}
