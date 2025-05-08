package com.example.githubapi.data.model



import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubapi.data.paging.RepoPagingSource
import com.example.githubapi.data.network.GitHubService
import kotlinx.coroutines.flow.Flow

class RepoGitHubRepository(private val api: GitHubService) {

    fun getTopRepositories(): Flow<PagingData<GitHubRepo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 50,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RepoPagingSource(api) }
        ).flow
    }
}