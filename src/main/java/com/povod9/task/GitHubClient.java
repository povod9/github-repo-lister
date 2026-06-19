package com.povod9.task;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class GitHubClient {

    private final RestClient restClient;

    public GitHubClient() {
        this.restClient = RestClient.builder().build();
    }

    public List<GitHubRepoInfo> repoList(String username){
        return restClient.method(HttpMethod.GET)
                .uri("https://api.github.com/users/{username}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GitHubRepoInfo>>() {});
    }

    public List<GitHubBranchInfo> branchList(String username,String repoName){
        return restClient.method(HttpMethod.GET)
                .uri("https://api.github.com/repos/{username}/{repo}/branches",username, repoName)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GitHubBranchInfo>>() {});
    }
}
