package com.povod9.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
class GitHubClient {

    private final RestClient restClient;

    GitHubClient(@Value("${github.api.url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    List<GitHubRepoInfo> repoList(String username){
        return restClient.method(HttpMethod.GET)
                .uri("/users/{username}/repos", username)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GitHubRepoInfo>>() {});
    }

    List<GitHubBranchInfo> branchList(String username,String repoName){
        return restClient.method(HttpMethod.GET)
                .uri("/repos/{username}/{repo}/branches",username, repoName)
                .retrieve()
                .body(new ParameterizedTypeReference<List<GitHubBranchInfo>>() {});
    }
}
