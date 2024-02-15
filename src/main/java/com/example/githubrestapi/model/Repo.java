package com.example.githubrestapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Repo {
    private String repoName;
    private String userName;
    private List<Branch> branches;
}
