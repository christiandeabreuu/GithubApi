package com.example.githubapi.data


class RepoGitHubRepository(private val api: GitHubService) {

    suspend fun getTopRepositories(page: Int): List<Repository> {
        return try {
            val response = api.getTopRepositories("language:kotlin", "stars", page)
            response.items
        } catch (e: Exception) {
            emptyList()
        }
    }
}