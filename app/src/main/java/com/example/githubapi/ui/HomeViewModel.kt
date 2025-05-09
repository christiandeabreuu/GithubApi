package com.example.githubapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.domain.RepoGitHubUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val githubUseCase: RepoGitHubUseCase) : ViewModel() {

    private val _repositories =
        MutableStateFlow<PagingData<GitHubRepo>>(PagingData.empty())
    val repositories: StateFlow<PagingData<GitHubRepo>> = _repositories

    fun getRepositories() {
        viewModelScope.launch {
            githubUseCase.execute()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _repositories.value = pagingData
                }
        }
    }
}
