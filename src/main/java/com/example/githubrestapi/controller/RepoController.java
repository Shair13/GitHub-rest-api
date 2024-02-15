package com.example.githubrestapi.controller;

import com.example.githubrestapi.model.Repo;
import com.example.githubrestapi.service.GitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class RepoController {

    private final GitService gitService;

    @GetMapping("/json/{login}")
    public Repo[] displayGit(@PathVariable String login) {
        return gitService.getUserRepositories(login);
    }
}
