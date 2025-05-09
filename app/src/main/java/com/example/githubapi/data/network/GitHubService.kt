package com.example.githubapi.data.network

import com.example.githubapi.data.model.GitHubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories")
    suspend fun getTopRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): GitHubResponse
}