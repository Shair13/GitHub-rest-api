package com.example.githubrestapi.controller;

import com.example.githubrestapi.exception.MissingAcceptHeaderException;
import com.example.githubrestapi.service.GitService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@RequiredArgsConstructor
@Controller
public class RepoController {

    private final GitService gitService;

    @GetMapping(path ="/{login}", headers = {"Accept=application/json"})
    public String displayGit(@PathVariable String login, Model model, @RequestHeader("Accept") String acceptHeader) {
        if(!acceptHeader.equals("application/json")) {
            throw new MissingAcceptHeaderException("Missing or invalid Accept header");
        }
        model.addAttribute("repos", gitService.getUserRepositories(login));
        return "display-repos";
    }
}
