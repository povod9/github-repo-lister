package com.povod9.task;

import java.util.List;

public interface GitHubClientService {

    List<GitHubRepoResponse> listOfRepos(String username);
}
