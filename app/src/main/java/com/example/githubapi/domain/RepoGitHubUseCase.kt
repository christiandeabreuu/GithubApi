package com.example.githubapi.domain

import com.example.githubapi.data.RepoGitHubRepository
import com.example.githubapi.data.Repository

class RepoGitHubUseCase(private val repository: RepoGitHubRepository) {

    suspend fun execute(page: Int): List<Repository> {
        return repository.getTopRepositories(page)
    }
}