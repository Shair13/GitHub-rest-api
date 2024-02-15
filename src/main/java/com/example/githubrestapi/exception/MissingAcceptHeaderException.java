package com.example.githubrestapi.exception;

public class MissingAcceptHeaderException extends RuntimeException {
    public MissingAcceptHeaderException(String message) {
        super(message);
    }
}
