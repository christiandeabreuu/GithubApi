package com.example.githubapi.domain

import com.example.githubapi.data.RepoGitHubRepository
import com.example.githubapi.data.Repository
import com.example.githubapi.data.Owner
import com.example.githubapi.data.RepositoryDao
import com.example.githubapi.data.RepositoryEntity

class RepoGitHubUseCase(private val repository: RepoGitHubRepository) {

    suspend fun execute(page: Int): List<Repository> {
        return repository.getTopRepositories(page).map { entity ->
            Repository(
                id = entity.id,
                name = entity.name,
                stargazers_count = entity.stargazersCount,
                forks_count = entity.forksCount,
                owner = Owner(entity.ownerName, entity.ownerAvatarUrl)
            )
        }
    }
}