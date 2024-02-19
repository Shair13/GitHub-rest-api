package com.example.githubrestapi.service;

import com.example.githubrestapi.model.Branch;
import com.example.githubrestapi.model.Repo;
import com.example.githubrestapi.webclient.dto.BranchDto;
import com.example.githubrestapi.webclient.dto.RepoDto;
import com.example.githubrestapi.webclient.gitclient.GitClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GitService {


    private final GitClient gitClient;

    public List<Repo> getUserRepositories(String login) {

        return notForkedRepos(gitClient.getReposForUser(login)).stream()
                .map(repoDto -> {
                    List<Branch> branchesList = Arrays.stream(gitClient.getBranchesForRepo(repoDto.getOwner().getLogin(), repoDto.getName()))
                            .map(branchDto -> new Branch(branchDto.getName(), branchDto.getCommit().getSha()))
                            .toList();
                   return new Repo(repoDto.getName(), repoDto.getOwner().getLogin(), branchesList);
                })
                .toList();
    }

    private List<RepoDto> notForkedRepos(RepoDto[] reposDto) {

        return Arrays.stream(reposDto)
                .filter(repoDto -> !repoDto.isFork())
                .toList();
    }
}
