package com.povod9.task;

public record GitHubRepoInfo(
        String name,
        boolean fork,
        Owner owner
) {
    public record Owner(String login){}
}
