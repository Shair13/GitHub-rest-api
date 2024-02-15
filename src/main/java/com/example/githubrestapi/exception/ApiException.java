package com.example.githubrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException {
    private final HttpStatus status;
    private final String message;
}
