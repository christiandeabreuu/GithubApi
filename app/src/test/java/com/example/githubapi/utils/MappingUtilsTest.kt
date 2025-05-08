package com.example.githubapi.utils


import com.example.githubapi.data.local.RepositoryEntity
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.data.model.Owner
import org.junit.Assert.assertEquals
import org.junit.Test

class MappingUtilsTest {

    @Test
    fun `entityToDomain should convert RepositoryEntity to GitHubRepo`() {
        val entity = RepositoryEntity(
            id = 1,
            name = "TestRepo",
            stargazers_count = 100,
            forks_count = 50,
            owner_login = "test_owner",
            owner_avatar_url = "https://test.avatar.com"
        )

        val domain = MappingUtils.entityToDomain(entity)

        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.stargazers_count, domain.stargazers_count)
        assertEquals(entity.forks_count, domain.forks_count)
        assertEquals(entity.owner_login, domain.owner.login)
        assertEquals(entity.owner_avatar_url, domain.owner.avatar_url)
    }

    @Test
    fun `domainToEntity should convert GitHubRepo to RepositoryEntity`() {
        val domain = GitHubRepo(
            id = 2,
            name = "AnotherRepo",
            stargazers_count = 200,
            forks_count = 75,
            owner = Owner("another_owner", "https://another.avatar.com")
        )

        val entity = MappingUtils.domainToEntity(domain)

        assertEquals(domain.id, entity.id)
        assertEquals(domain.name, entity.name)
        assertEquals(domain.stargazers_count, entity.stargazers_count)
        assertEquals(domain.forks_count, entity.forks_count)
        assertEquals(domain.owner.login, entity.owner_login)
        assertEquals(domain.owner.avatar_url, entity.owner_avatar_url)
    }
}
