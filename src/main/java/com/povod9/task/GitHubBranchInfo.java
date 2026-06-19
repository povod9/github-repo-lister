package com.povod9.task;

public record GitHubBranchInfo(
        String name,
        Commit commit
) {
    public record Commit(String sha){}
}
