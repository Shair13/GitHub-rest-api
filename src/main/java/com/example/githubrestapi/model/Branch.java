package com.example.githubrestapi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Branch {
    private String branchName;
    private String sha;
}
