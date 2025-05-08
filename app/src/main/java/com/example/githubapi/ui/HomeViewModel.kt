package com.example.githubapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.domain.RepoGitHubUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val usecase: RepoGitHubUseCase) : ViewModel() {

    private val _repositories = MutableLiveData<List<GitHubRepo>>()
    val repositories: LiveData<List<GitHubRepo>> = _repositories

    fun getRepositories() {
        viewModelScope.launch {
            val data = usecase.execute()
            _repositories.postValue(data)
        }
    }
}