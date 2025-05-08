package com.example.githubapi.data.model


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.githubapi.utils.MappingUtils
import com.example.githubapi.data.local.GitHubDatabase
import com.example.githubapi.data.network.GitHubService
import com.example.githubapi.data.paging.GitHubRepoPagingMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepoGitHubRepository(private val api: GitHubService, private val database: GitHubDatabase) {

    @OptIn(ExperimentalPagingApi::class)
    fun getTopRepositories(): Flow<PagingData<GitHubRepo>> {
        return Pager(config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
            remoteMediator = GitHubRepoPagingMediator("language:kotlin", database, api),
            pagingSourceFactory = {
                database.repositoryDao().getTopRepositories()
            }).flow.map { pagingData ->
            pagingData.map { MappingUtils.entityToDomain(it) }
        }
    }
}
