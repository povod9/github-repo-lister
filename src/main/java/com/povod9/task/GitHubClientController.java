package com.povod9.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GitHubClientController {

    private final GitHubClientService service;

    @GetMapping("/users/{username}")
    public ResponseEntity<List<GitHubRepoResponse>> listOfRepos(@PathVariable String username){
        List<GitHubRepoResponse> repoList = service.listOfRepos(username);
        return ResponseEntity.ok(repoList);
    }
}
