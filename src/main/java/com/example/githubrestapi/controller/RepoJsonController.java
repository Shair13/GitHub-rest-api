package com.example.githubrestapi.controller;

import com.example.githubrestapi.model.Repo;
import com.example.githubrestapi.service.GitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RepoJsonController {

    private final GitService gitService;

    @GetMapping("/json/{login}")
    public List<Repo> displayGit(@PathVariable String login) {
        return gitService.getUserRepositories(login);
    }
}
