package com.example.githubapi.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM repositories ORDER BY stargazers_count DESC")
    fun getTopRepositories(): PagingSource<Int, RepositoryEntity> // Mantendo para paginação
}