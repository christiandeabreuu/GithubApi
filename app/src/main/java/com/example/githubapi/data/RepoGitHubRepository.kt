package com.example.githubapi.data



import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.example.githubapi.data.RepoPagingSource

class RepoGitHubRepository(private val api: GitHubService) {

    fun getTopRepositories(): Flow<PagingData<Repository>> {
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