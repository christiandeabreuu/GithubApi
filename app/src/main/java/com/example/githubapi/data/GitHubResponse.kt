package com.example.githubapi.data

data class GitHubResponse(
    val items: List<Repository>
)

data class Repository(
    val name: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner: Owner
)

data class Owner(
    val login: String,
    val avatar_rl: String
)