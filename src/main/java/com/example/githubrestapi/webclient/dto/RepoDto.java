package com.example.githubrestapi.webclient.dto;

public record RepoDto(OwnerDto owner, String name, boolean fork, BranchDto[] branches) {

}
