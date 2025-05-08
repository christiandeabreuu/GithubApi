package com.example.githubapi.domain

import androidx.paging.PagingData
import com.example.githubapi.data.model.RepoGitHubRepository
import com.example.githubapi.data.model.GitHubRepo
import kotlinx.coroutines.flow.Flow

class RepoGitHubUseCase(private val repository: RepoGitHubRepository) {

    suspend fun execute(): List<GitHubRepo> {
        return repository.getTopRepositories()
    }
}