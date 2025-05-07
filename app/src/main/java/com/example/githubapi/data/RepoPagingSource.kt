package com.example.githubapi.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubapi.data.GitHubService
import com.example.githubapi.data.Repository

class RepoPagingSource(private val api: GitHubService) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val page = params.key ?: 1
        return try {
            val response = api.getTopRepositories("language:kotlin", "stars", page)
            LoadResult.Page(
                data = response.items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.items.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
        }
    }
}