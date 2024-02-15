package com.example.githubrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GitHubRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitHubRestApiApplication.class, args);
    }

}
