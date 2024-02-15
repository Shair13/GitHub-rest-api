package com.example.githubrestapi.exception;

public class GitHubApiRemoteException extends RuntimeException  {
    public GitHubApiRemoteException(String message){
        super(message);
    }
}
