package com.example.githubrestapi.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Repo {
    private String repoName;
    private String userName;
    private List<Branch> branches;

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
