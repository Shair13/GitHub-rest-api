package com.example.githubrestapi.controller;

import com.example.githubrestapi.service.GitService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class RepoJsonController {

    private final GitService gitService;

    @GetMapping("/{login}")
    public String displayGit(@PathVariable String login, Model model) {
        model.addAttribute("repos", gitService.getUserRepositories(login));
        return "display-repos";
    }
}
