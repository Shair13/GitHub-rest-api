package com.example.githubrestapi.webclient.gitclient;


import com.example.githubrestapi.exception.GitHubApiRemoteException;
import com.example.githubrestapi.webclient.dto.BranchDto;
import com.example.githubrestapi.webclient.dto.RepoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GitClient {

    private static final String GIT_API_URL = "https://api.github.com/";

    @Value("${github.api.token}")
    private String TOKEN;
    RestClient restClient = RestClient.create();

    public List<RepoDto> getReposForUser(String user) {
        return Arrays.stream(fetchData("users/{user}/repos", RepoDto[].class, user)).toList();
    }

    public List<BranchDto> getBranchesForRepo(String user, String repo) {
        return Arrays.stream(fetchData("repos/{user}/{repo}/branches", BranchDto[].class, user, repo)).toList();
    }

    private <T> T fetchData(String url, Class<T> responseType, Object... uriVariables) {
        try {
            return restClient.get()
                    .uri(GIT_API_URL + url, uriVariables)
                    .header("Authorization", TOKEN)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(responseType);
        } catch (HttpClientErrorException.NotFound e) {
            throw new GitHubApiRemoteException("No user found with this login");
        } catch (HttpClientErrorException.Forbidden e) {
            throw new GitHubApiRemoteException("Error while reaching external GitHub API");
        }
    }
}