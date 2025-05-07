package com.example.githubapi.data

import com.example.githubapi.data.RepositoryDao
import com.example.githubapi.data.RepositoryEntity
import com.example.githubapi.data.GitHubService

class RepoGitHubRepository(
    private val api: GitHubService,
    private val dao: RepositoryDao
) {

    suspend fun getTopRepositories(page: Int): List<RepositoryEntity> {
        return try {
            val response = api.getTopRepositories("language:kotlin", "stars", page)
            val entities = response.items.map { repo ->
                RepositoryEntity(
                    id = repo.id,
                    name = repo.name,
                    stargazersCount = repo.stargazers_count,
                    forksCount = repo.forks_count,
                    ownerName = repo.owner.login,
                    ownerAvatarUrl = repo.owner.avatar_url
                )
            }

            dao.insertRepositories(entities) // Salva no banco
            entities // Retorna os dados recém-salvos
        } catch (e: Exception) {
            dao.getRepositories() // Retorna dados armazenados se houver erro
        }
    }
}