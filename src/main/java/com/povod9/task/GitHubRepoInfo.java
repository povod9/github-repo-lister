package com.povod9.task;

record GitHubRepoInfo(
        String name,
        boolean fork,
        Owner owner
) {
    record Owner(String login){}
}
