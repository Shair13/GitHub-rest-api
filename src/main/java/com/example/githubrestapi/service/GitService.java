package com.example.githubrestapi.service;

import com.example.githubrestapi.model.Branch;
import com.example.githubrestapi.model.Repo;
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

    public Repo[] getUserRepositories(String login) {

        RepoDto[] reposDto = gitClient.getReposForUser(login);

        for (RepoDto repo : reposDto) {
            repo.setBranches(gitClient.getBranchesForRepo(repo.getOwner().getLogin(), repo.getName()));
        }

//        RepoDto[] ownRepos = Arrays.stream(reposDto).filter(repo -> !repo.isFork()).toArray(RepoDto[]::new);

        RepoDto[] ownRepos = notForkedRepos(reposDto);

        List<Repo> repoList = new ArrayList<>();

        for (RepoDto repo : ownRepos) {
            repoList.add(Repo.builder()
                    .userName(repo.getOwner().getLogin())
                    .repoName(repo.getName())
                    .branches(Arrays.stream(repo.getBranches())
                            .map(branchDto -> {
                                Branch branch = new Branch();
                                branch.setBranchName(branchDto.getName());
                                branch.setSha(branchDto.getCommit().getSha());
                                return branch;
                            })
                            .toArray(Branch[]::new))
                    .build());
        }

        return  repoList.toArray(new Repo[0]);
    }

    private RepoDto[] notForkedRepos(RepoDto[] reposDto) {

        //       return Arrays.stream(reposDto).filter(repo -> !repo.isFork()).toArray(RepoDto[]::new);

        List<RepoDto> ownReposList = new ArrayList<>();
        for (RepoDto repoDto : reposDto) {
            if (!repoDto.isFork()) {
                ownReposList.add(repoDto);
            }
        }
        return ownReposList.toArray(new RepoDto[0]);
    }
}
