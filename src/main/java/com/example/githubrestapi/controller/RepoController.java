package com.example.githubrestapi.controller;

import com.example.githubrestapi.exception.GitHubApiUserNotFoundException;
import com.example.githubrestapi.model.Repo;
import com.example.githubrestapi.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class RepoController {

    private final GitService gitService;

    @GetMapping("/json/{login}")
    public List<Repo> displayGit(@PathVariable String login) {
        return gitService.getUserRepositories(login);
    }
}
