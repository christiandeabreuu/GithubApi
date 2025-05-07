package com.example.githubapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import com.example.githubapi.data.RepoGitHubRepository
import com.example.githubapi.data.Repository

class HomeViewModel(private val repository: RepoGitHubRepository) : ViewModel() {

    fun getRepositories(): Flow<PagingData<Repository>> {
        return repository.getTopRepositories().cachedIn(viewModelScope)
    }

}