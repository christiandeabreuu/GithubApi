package com.example.githubapi.domain

import androidx.paging.PagingData
import com.example.githubapi.data.RepoGitHubRepository
import com.example.githubapi.data.GitHubRepo
import kotlinx.coroutines.flow.Flow

class RepoGitHubUseCase(private val repository: RepoGitHubRepository) {

    fun execute(): Flow<PagingData<GitHubRepo>> {
        return repository.getTopRepositories()
    }
}