package com.example.githubapi.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRepositories(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM repositories ORDER BY stargazers_count DESC")
    fun getTopRepositories(): PagingSource<Int, RepositoryEntity> // 🔥 Agora retorna um PagingSource para paginação

    @Query("DELETE FROM repositories")
    suspend fun clearTopRepositories()
}