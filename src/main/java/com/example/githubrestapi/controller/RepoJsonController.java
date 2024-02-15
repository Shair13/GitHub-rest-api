package com.example.githubrestapi.controller;

import com.example.githubrestapi.service.GitService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RepoJsonController {

    private final GitService gitService;

    @RequestMapping("/{login}")
    public String displayGit(@PathVariable String login, Model model) {
        model.addAttribute("repos", gitService.getUserRepositories(login));
        return "display-repos";
    }
}
