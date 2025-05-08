package com.example.githubapi.data.paging


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.githubapi.data.local.GitHubDatabase
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.data.model.Owner
import com.example.githubapi.data.network.GitHubService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GitHubRepoPagingMediatorTest {

    private lateinit var database: GitHubDatabase
    private lateinit var service: GitHubService
    private lateinit var mediator: GitHubRepoPagingMediator

    @Before
    fun setup() {
        database = mockk(relaxed = true)
        service = mockk(relaxed = true)
        mediator = GitHubRepoPagingMediator("language:kotlin", database, service)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `load REFRESH should clear database and insert new data`() = runBlocking {
        // Simula uma chamada da API que retorna uma lista de repositórios
        coEvery { service.getTopRepositories(any(), any(), any()) } returns mockk {
            every { items } returns listOf(
                GitHubRepo(1, "Repo1", 100, 50, Owner("owner", "avatar")), // 🔥 Agora retorna `GitHubRepo` diretamente
                GitHubRepo(2, "Repo2", 200, 75, Owner("owner2", "avatar2"))
            )
        }

        // Executa a paginação no modo REFRESH
        val result = mediator.load(LoadType.REFRESH, PagingState(emptyList(), null, PagingConfig(10), 10))

        // Verifica se os dados antigos foram limpos
        coVerify { database.repositoryDao().clearTopRepositories() }

        // Verifica se os novos dados foram inseridos corretamente
        coVerify { database.repositoryDao().insertTopRepositories(any()) }

        // Confirma que a operação foi bem-sucedida
        assertTrue(result is RemoteMediator.MediatorResult.Success)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `load APPEND should add new data`() = runBlocking {
        // Simula a API retornando novos repositórios para adicionar à lista existente
        coEvery { service.getTopRepositories(any(), any(), any()) } returns mockk {
            every { items } returns listOf(
                GitHubRepo(3, "Repo3", 300, 100, Owner("owner3", "avatar3"))
            )
        }

        // Executa a paginação no modo APPEND (adicionar mais itens)
        val result = mediator.load(LoadType.APPEND, PagingState(emptyList(), null, PagingConfig(10), 10))

        // Verifica se novos itens foram adicionados ao banco
        coVerify { database.repositoryDao().insertTopRepositories(any()) }

        // Confirma que a operação foi bem-sucedida
        assertTrue(result is RemoteMediator.MediatorResult.Success)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `load PREPEND should not fetch data`() = runBlocking {
        // Executa a paginação no modo PREPEND (não deve carregar novos itens)
        val result = mediator.load(LoadType.PREPEND, PagingState(emptyList(), null, PagingConfig(10), 10))

        // Confirma que a paginação terminou e não houve carregamento
        assertTrue(result is RemoteMediator.MediatorResult.Success && result.endOfPaginationReached)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `load should return error when API fails`() = runBlocking {
        // Simula uma falha na API para testar o tratamento de erro
        coEvery { service.getTopRepositories(any(), any(), any()) } throws Exception("API failure")

        // Executa a paginação e espera um erro
        val result = mediator.load(LoadType.REFRESH, PagingState(emptyList(), null, PagingConfig(10), 10))

        // Confirma que um erro foi retornado corretamente
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }

}