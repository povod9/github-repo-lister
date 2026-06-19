package com.povod9.task;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(webEnvironment =
SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = "github.api.url=http://localhost:8089")
public class GitHubIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private WireMockServer wireMockService;

    @BeforeEach
    void setUp(){
        wireMockService = new WireMockServer(8089);
        wireMockService.start();
        configureFor("localhost", 8089);
    }

    @AfterEach
    void tearDown(){
        wireMockService.stop();
    }

    @Test
    void shouldReturnFilteredRepoSuccessfully() throws Exception {
        stubFor(get(urlEqualTo("/users/povod9/repos"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                [
                                 {
                                  "name": "project",
                                  "fork": false,
                                  "owner": { "login": "povod9" } 
                                 },
                                 {
                                  "name": "project",
                                  "fork": true,
                                  "owner": { "login": "povod9" } 
                                 }
                                ]
                                """)));

        stubFor(get(urlEqualTo("/repos/povod9/project/branches"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                [
                                 {
                                  "name": "main",
                                  "commit": { "sha": "124513513" }
                                 }
                                ]
                                """)));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/povod9"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].repoName").value("project"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].login").value("povod9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches[0].name").value("main"));
    }


    @Test
    void correctExceptionMessage() throws Exception {
        stubFor(get(urlEqualTo("/users/povod9/repos"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                 {
                                  "status": 404,
                                  "message": "User not found on GitHub"
                                 }
                                """)));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/povod9"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User not found on GitHub"));

    }
}
