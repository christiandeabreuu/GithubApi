package com.example.githubapi

import com.example.githubapi.data.local.RepositoryEntity
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.data.model.Owner

object MappingUtils {

    fun entityToDomain(entity: RepositoryEntity): GitHubRepo {
        return GitHubRepo(
            id = entity.id,
            name = entity.name,
            stargazers_count = entity.stargazers_count,
            forks_count = entity.forks_count,
            owner = Owner(entity.owner_login, entity.owner_avatar_url)
        )
    }

    fun domainToEntity(domain: GitHubRepo): RepositoryEntity {
        return RepositoryEntity(
            id = domain.id,
            name = domain.name,
            stargazers_count = domain.stargazers_count,
            forks_count = domain.forks_count,
            owner_login = domain.owner.login,
            owner_avatar_url = domain.owner.avatar_url
        )
    }
}