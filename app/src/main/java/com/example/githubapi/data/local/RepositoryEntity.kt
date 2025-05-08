package com.example.githubapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.data.model.Owner

@Entity(tableName = "repositories")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner_login: String,
    val owner_avatar_url: String
)

fun RepositoryEntity.toDomain(): GitHubRepo {
    return GitHubRepo(
        id = this.id,
        name = this.name,
        stargazers_count = this.stargazers_count,
        forks_count = this.forks_count,
        owner = Owner(this.owner_login, this.owner_avatar_url)
    )
}

