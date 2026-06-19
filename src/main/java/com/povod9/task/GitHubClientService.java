package com.povod9.task;

import java.util.List;

interface GitHubClientService {
    List<GitHubRepoResponse> listOfRepos(String username);
}
