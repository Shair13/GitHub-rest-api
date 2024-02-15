package com.example.githubrestapi.webclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepoDto {

    private OwnerDto owner;
    private String name;
    private boolean fork;
    private BranchDto[] branches;
}
