package com.example.githubrestapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Branch {
    private String branchName;
    private String sha;
}
