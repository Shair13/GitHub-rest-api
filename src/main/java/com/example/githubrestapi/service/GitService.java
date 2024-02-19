package com.example.githubrestapi.service;

import com.example.githubrestapi.model.Branch;
import com.example.githubrestapi.model.Repo;
import com.example.githubrestapi.webclient.dto.RepoDto;
import com.example.githubrestapi.webclient.gitclient.GitClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GitService {


    private final GitClient gitClient;

    public List<Repo> getUserRepositories(String login) {

        return notForkedRepos(gitClient.getReposForUser(login)).stream()
                .map(repoDto -> {
                    List<Branch> branchesList = gitClient.getBranchesForRepo(repoDto.owner().login(), repoDto.name()).stream()
                            .map(branchDto -> new Branch(branchDto.name(), branchDto.commit().sha()))
                            .toList();
                   return new Repo(repoDto.name(), repoDto.owner().login(), branchesList);
                })
                .toList();
    }

    private List<RepoDto> notForkedRepos(List<RepoDto> reposDto) {

        return reposDto.stream()
                .filter(repoDto -> !repoDto.fork())
                .toList();
    }
}
