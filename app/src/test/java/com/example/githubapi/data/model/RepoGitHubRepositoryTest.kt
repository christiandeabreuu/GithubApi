package com.example.githubapi.data.model

import androidx.paging.*
import com.example.githubapi.data.local.GitHubDatabase
import com.example.githubapi.data.local.RepositoryDao
import com.example.githubapi.data.local.RepositoryEntity
import com.example.githubapi.data.network.GitHubService
import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RepoGitHubRepositoryTest {

    private lateinit var repository: RepoGitHubRepository
    private val api = mockk<GitHubService>()
    private val database = mockk<GitHubDatabase>()
    private val dao = mockk<RepositoryDao>()

    @Before
    fun setup() {
        every { database.repositoryDao() } returns dao // 🔥 Simula a conexão com o DAO
        repository = RepoGitHubRepository(api, database)
    }

    @Test
    fun `getTopRepositories should return PagingData Flow`() = runBlocking {
        // PagingSource fake para simular a resposta do banco de dados
        val mockPagingSource = object : PagingSource<Int, RepositoryEntity>() {
            override fun getRefreshKey(state: PagingState<Int, RepositoryEntity>): Int? {
                return state.anchorPosition?.let { position ->
                    state.closestItemToPosition(position)?.id // Retorna a chave de refresh para paginação
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryEntity> {
                return LoadResult.Page(
                    data = listOf(
                        RepositoryEntity(1, "Repo1", 100, 50, "owner1", "https://avatar.com/owner1"),
                        RepositoryEntity(2, "Repo2", 200, 75, "owner2", "https://avatar.com/owner2")
                    ), // Lista simulada de repositórios para teste
                    prevKey = null, // Sem chave anterior (início da paginação)
                    nextKey = null // Sem chave seguinte (fim da paginação)
                )
            }
        }

        coEvery { dao.getTopRepositories() } returns mockPagingSource // Simula o retorno do banco

        val result = repository.getTopRepositories().first() // Coleta o primeiro valor do Flow

        assertTrue(result is PagingData<GitHubRepo>) // Garante que o retorno é um PagingData válido
    }

    @Test
    fun `getTopRepositories should use correct PagingSource`() = runBlocking {
        // PagingSource fake para simular a resposta do banco de dados
        val mockPagingSource = object : PagingSource<Int, RepositoryEntity>() {
            override fun getRefreshKey(state: PagingState<Int, RepositoryEntity>): Int? {
                return state.anchorPosition?.let { position ->
                    state.closestItemToPosition(position)?.id // Retorna a chave de refresh para paginação
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryEntity> {
                return LoadResult.Page(
                    data = listOf(
                        RepositoryEntity(1, "Repo1", 100, 50, "owner1", "https://avatar.com/owner1"),
                        RepositoryEntity(2, "Repo2", 200, 75, "owner2", "https://avatar.com/owner2")
                    ), //  Lista simulada de repositórios para teste
                    prevKey = null,
                    nextKey = null
                )
            }
        }

        coEvery { dao.getTopRepositories() } returns mockPagingSource //  Simula o retorno do banco

        repository.getTopRepositories().first() // Dispara a chamada para testar

        verify { dao.getTopRepositories() } // Confirma que o método correto do DAO foi chamado
    }
}