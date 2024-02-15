package com.example.githubrestapi.webclient.dto;

import lombok.Getter;

@Getter
public class BranchDto {
    private String name;
    private CommitDto commit;
}
