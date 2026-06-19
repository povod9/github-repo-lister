package com.povod9.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubClientServiceImpl implements GitHubClientService{

    private final GitHubClient client;

    @Override
    public List<GitHubRepoResponse> listOfRepos(String username) {
        return client.repoList(username).stream()
                .filter(repo -> !repo.fork())
                .map(repo -> {
                            return GitHubRepoResponse.builder()
                                    .repoName(repo.name())
                                    .login(repo.owner().login())
                                    .branches(client.branchList(username, repo.name()))
                                    .build();
                }
                ).toList();
    }
}
