package com.example.githubapi.data



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val stargazersCount: Int,
    val forksCount: Int,
    val ownerName: String,
    val ownerAvatarUrl: String
)