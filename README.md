# GitHub Repositories Proxy API

This is a Spring Boot REST API application that consumes the public GitHub API to fetch a user's repositories. It filters out all fork repositories and returns a clean, JSON response containing the repository name, owner login, and a list of branches with their last commit SHA.

## Tech Stack
* **Java 25**
* **Spring Boot 4.1.0** 
* **Spring RestClient**
* **Gradle** (Kotlin DSL)
* **Lombok**
* **WireMock**
* **JUnit 5 & MockMvc**

## Prerequisites
* **Java 25** installed on your machine.
* Port `8080` available for the application.

## How to Run the Application

You can run the application directly using the Gradle wrapper. In the root directory of the project, execute:

### On Windows:
```powershall
.\gradlew.bat bootRun
.\gradlew.bat test
```

### On Linux/macOS:
```bash
./gradlew bootRun
./gradlew test
```


## API Documentation

### 1. Get User's Repositories
Retrieves a list of non-fork repositories for a specific GitHub user.

* **Endpoint:** `GET /users/{username}`
* **Headers:** `Accept: application/json`

**Success Response (200 OK):**
```json
[
  {
    "repoName": "project",
    "login": "username",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "124513513"
      }
    ]
  }
]
```

### 2. User Not Found

If the GitHub user does not exist, the API returns a standard 404 response.


**Error Response (404 Not Found):**

```json
{
  "status": 404,
  "message": "User not found on GitHub"
}
```