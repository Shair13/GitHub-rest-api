package com.example.githubrestapi.webclient.gitclient;


import com.example.githubrestapi.webclient.dto.BranchDto;
import com.example.githubrestapi.webclient.dto.RepoDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitClient {

    private static final String GIT_API_URL = "https://api.github.com/";
    private final RestTemplate restTemplate = new RestTemplate();


    public RepoDto[] getReposForUser(String user) {
        return fetchData("users/{user}/repos", RepoDto[].class, user);
    }

    public BranchDto[] getBranchesForRepo(String user, String repo) {
        return fetchData("repos/{user}/{repo}/branches", BranchDto[].class, user, repo);
    }

    private <T> T fetchData(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(GIT_API_URL + url, responseType, objects);
    }
}
