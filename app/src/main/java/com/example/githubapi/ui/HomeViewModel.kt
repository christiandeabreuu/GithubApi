package com.example.githubapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapi.data.GitHubRepo
import com.example.githubapi.domain.RepoGitHubUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val usecase: RepoGitHubUseCase) : ViewModel() {

    private val _repositories = MutableLiveData<PagingData<GitHubRepo>>(PagingData.empty())
    val repositories: LiveData<PagingData<GitHubRepo>> = _repositories

    fun getRepositories() {
        viewModelScope.launch {
            usecase.execute().cachedIn(viewModelScope).collect { pagingData ->
                _repositories.value = pagingData
            }
        }
    }
}