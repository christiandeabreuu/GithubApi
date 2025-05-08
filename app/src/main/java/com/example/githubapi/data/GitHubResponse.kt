package com.example.githubapi.data

data class GitHubResponse(
    val items: List<GitHubRepo>
)

data class GitHubRepo(
    val id: Int,
    val name: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner: Owner
)

data class Owner(
    val login: String,
    val avatar_url: String
)