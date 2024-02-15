package com.example.githubrestapi.exception;

public class GitHubApiUserNotFoundException extends RuntimeException  {
    public GitHubApiUserNotFoundException(String message){
        super(message);
    }
}
