package com.example.githubrestapi.webclient.gitclient;


import com.example.githubrestapi.exception.GitHubApiRemoteException;
import com.example.githubrestapi.webclient.dto.BranchDto;
import com.example.githubrestapi.webclient.dto.RepoDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GitClient {

    private static final String GIT_API_URL = "https://api.github.com/";
    private static final String TOKEN = "YourGitHubToken";
    private final RestTemplate restTemplate = new RestTemplate();

    public RepoDto[] getReposForUser(String user) {
        return fetchData("users/{user}/repos", RepoDto[].class, user);
    }

    public BranchDto[] getBranchesForRepo(String user, String repo) {
        return fetchData("repos/{user}/{repo}/branches", BranchDto[].class, user, repo);
    }

    private <T> T fetchData(String url, Class<T> responseType, Object... objects) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", "application/json");
            httpHeaders.set("Authorization", TOKEN);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(GIT_API_URL + url, HttpMethod.GET, entity, responseType, objects);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new GitHubApiRemoteException("No user found with this login");
        } catch (RuntimeException e) {
            throw new GitHubApiRemoteException("Error while reaching external GitHub API");
        }
    }
}