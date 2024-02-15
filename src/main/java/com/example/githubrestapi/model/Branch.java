package com.example.githubrestapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Branch {
    private String branchName;
    private String sha;
}
