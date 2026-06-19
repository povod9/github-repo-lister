package com.povod9.task;

import lombok.Builder;
import java.util.List;

@Builder
public record GitHubRepoResponse(
        String repoName,
        String login,
        List<GitHubBranchInfo> branches
){}
