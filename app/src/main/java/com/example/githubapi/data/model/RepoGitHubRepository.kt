package com.example.githubapi.data.model



import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubapi.MappingUtils
import com.example.githubapi.data.local.GitHubDatabase
import com.example.githubapi.data.paging.RepoPagingSource
import com.example.githubapi.data.network.GitHubService
import kotlinx.coroutines.flow.Flow

class RepoGitHubRepository(private val api: GitHubService, private val database: GitHubDatabase) {

    suspend fun getTopRepositories(): List<GitHubRepo> {
        val cachedRepos = database.repositoryDao().getTopRepositories() // 🔥 Primeiro busca do banco

        return if (cachedRepos.isNotEmpty()) {
            cachedRepos.map { MappingUtils.entityToDomain(it) } // 🔥 Converte os dados armazenados
        } else {
            val fetchedRepos = api.getTopRepositories("language:kotlin", "stars", 1).items
            database.repositoryDao().insertTopRepositories(fetchedRepos.map { MappingUtils.domainToEntity(it) }) // 🔥 Salva no banco
            fetchedRepos // 🔥 Retorna os dados da API
        }
    }
}