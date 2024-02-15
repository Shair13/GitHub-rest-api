package com.example.githubrestapi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Repo {
    private String repoName;
    private String userName;
    private Branch[] branches;
}
