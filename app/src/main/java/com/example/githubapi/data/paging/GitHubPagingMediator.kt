package com.example.githubapi.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.githubapi.data.local.GitHubDatabase
import com.example.githubapi.data.local.RepositoryEntity
import com.example.githubapi.data.network.GitHubService
import com.example.githubapi.utils.MappingUtils

@OptIn(ExperimentalPagingApi::class)
class GitHubRepoPagingMediator(
    private val query: String,
    private val database: GitHubDatabase,
    private val networkService: GitHubService
) : RemoteMediator<Int, RepositoryEntity>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, RepositoryEntity>
    ): MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val nextKey = lastItem?.let { state.pages.size + 1 } ?: 2
                    nextKey
                }
            }

            val response = networkService.getTopRepositories(query, "stars", page)
            val repos = response.items.map { MappingUtils.domainToEntity(it) }

            if (loadType == LoadType.REFRESH) {
                database.repositoryDao().clearTopRepositories()
            }
            database.repositoryDao().insertTopRepositories(repos)

            val endOfPaginationReached = repos.isEmpty() || repos.size < 10

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}