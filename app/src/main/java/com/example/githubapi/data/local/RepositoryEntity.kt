package com.example.githubapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner_login: String,
    val owner_avatar_url: String
)


