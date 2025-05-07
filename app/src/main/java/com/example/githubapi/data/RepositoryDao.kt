package com.example.githubapi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepositoryDao {

    @Insert
    suspend fun insertRepositories(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM repositories ORDER BY stargazersCount DESC")
    suspend fun getRepositories(): List<RepositoryEntity>
}