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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GitService {


    private final GitClient gitClient;

    public List<Repo> getUserRepositories(String login) {

        List<RepoDto> reposDto = notForkedRepos(gitClient.getReposForUser(login));

        List<Repo> repoList;

        repoList = reposDto.stream()
                .map(repoDto -> Repo.builder()
                        .userName(repoDto.getOwner().getLogin())
                        .repoName(repoDto.getName())
                        .build())
                .collect(Collectors.toList());

        repoList.forEach(repo -> {
            BranchDto[] branchesDto = gitClient.getBranchesForRepo(repo.getUserName(), repo.getRepoName());
            List<Branch> branches = Arrays.stream(branchesDto)
                    .map(branchDto -> Branch.builder()
                            .branchName(branchDto.getName())
                            .sha(branchDto.getCommit().getSha())
                            .build())
                    .collect(Collectors.toList());
            repo.setBranches(branches);
        });

        return repoList;
    }

    private List<RepoDto> notForkedRepos(RepoDto[] reposDto) {

        return Arrays.stream(reposDto)
                .filter(repoDto -> !repoDto.isFork())
                .collect(Collectors.toList());
    }
}
