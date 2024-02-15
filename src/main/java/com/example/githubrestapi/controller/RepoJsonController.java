package com.example.githubrestapi.controller;

import com.example.githubrestapi.exception.MissingAcceptHeaderException;
import com.example.githubrestapi.model.Repo;
import com.example.githubrestapi.service.GitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RepoJsonController {

    private final GitService gitService;

    @GetMapping(path = "/json/{login}", headers = {"Accept=application/json"})
    public List<Repo> displayGit(@PathVariable String login, @RequestHeader("Accept") String acceptHeader) {
        if(!acceptHeader.equals("application/json")) {
           throw new MissingAcceptHeaderException("Missing or invalid Accept header");
        }
        return gitService.getUserRepositories(login);
    }
}
