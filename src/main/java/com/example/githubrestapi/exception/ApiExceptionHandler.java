package com.example.githubrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {GitHubApiRemoteException.class})
    public ResponseEntity<Object> handleGitHubApiRemoteException(GitHubApiRemoteException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                notFound,
                e.getMessage()
        );
        return new ResponseEntity<>(apiException, notFound);
    }

}
