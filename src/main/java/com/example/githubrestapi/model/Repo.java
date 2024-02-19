package com.example.githubrestapi.model;

import java.util.List;

public record  Repo(String repoName, String userName, List<Branch> branches) {

}
