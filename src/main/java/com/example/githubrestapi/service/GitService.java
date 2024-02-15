package com.example.githubrestapi.service;

import com.example.githubrestapi.model.Branch;
import com.example.githubrestapi.model.Repo;
import com.example.githubrestapi.webclient.dto.BranchDto;
import com.example.githubrestapi.webclient.dto.RepoDto;
import com.example.githubrestapi.webclient.gitclient.GitClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitService {


    private final GitClient gitClient;

    public List<Repo> getUserRepositories(String login) {

        List<RepoDto> reposDto = notForkedRepos(gitClient.getReposForUser(login));

        List<Repo> repoList = new ArrayList<>();

        for (RepoDto repo : reposDto) {
            repoList.add(Repo.builder()
                    .userName(repo.getOwner().getLogin())
                    .repoName(repo.getName())
                    .build());
        }

        for (Repo repo : repoList) {
            BranchDto[] branchesDto = gitClient.getBranchesForRepo(repo.getUserName(), repo.getRepoName());
            List<Branch> branches = new ArrayList<>();
            Arrays.stream(branchesDto).forEach(branchDto -> {
                Branch branch = new Branch();
                branch.setBranchName(branchDto.getName());
                branch.setSha(branchDto.getCommit().getSha());
                branches.add(branch);
            });
            repo.setBranches(branches);
        }

        return repoList;
    }

    private List<RepoDto> notForkedRepos(RepoDto[] reposDto) {

        List<RepoDto> ownReposList = new ArrayList<>();
        for (RepoDto repoDto : reposDto) {
            if (!repoDto.isFork()) {
                ownReposList.add(repoDto);
            }
        }
        return ownReposList;
    }
}
