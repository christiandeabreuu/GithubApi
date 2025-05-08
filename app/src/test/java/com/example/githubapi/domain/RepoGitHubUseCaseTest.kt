package com.example.githubapi.domain


import androidx.paging.PagingData
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.data.model.Owner
import com.example.githubapi.data.model.RepoGitHubRepository
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlinx.coroutines.flow.first


class RepoGitHubUseCaseTest {

    private val repository = mockk<RepoGitHubRepository>()
    private val useCase = RepoGitHubUseCase(repository)

    @Test
    fun `execute should return PagingData Flow from repository`() = runBlocking {
        // Cria um objeto
        val mockPagingData = PagingData.from(listOf(
            GitHubRepo(1, "Repo1", 100, 50, Owner("owner1", "https://avatar.com/owner1")),
            GitHubRepo(2, "Repo2", 200, 75, Owner("owner2", "https://avatar.com/owner2"))
        ))

        // Simula o comportamento do repository para retornar um Flow com os dados esperados
        coEvery { repository.getTopRepositories() } returns flowOf(mockPagingData)

        // Executa o caso de uso e coletamos o primeiro valor do Flow
        val result = useCase.execute().first()

        // Verifica  os dados
        assertEquals(mockPagingData, result)
    }
}