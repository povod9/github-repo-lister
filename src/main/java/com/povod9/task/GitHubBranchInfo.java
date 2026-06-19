package com.povod9.task;

record GitHubBranchInfo(
        String name,
        Commit commit
) {
    record Commit(String sha){}
}
