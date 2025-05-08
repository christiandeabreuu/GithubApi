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
    suspend fun getTopRepositories(): List<RepositoryEntity> // 🔥 Agora retorna uma lista normal

    @Query("DELETE FROM repositories")
    suspend fun clearTopRepositories() // 🔥 Adicionamos para limpar antes de inserir novos dados
}