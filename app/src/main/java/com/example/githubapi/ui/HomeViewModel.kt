package com.example.githubapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.githubapi.domain.RepoGitHubUseCase
import com.example.githubapi.data.Repository

class HomeViewModel(private val useCase: RepoGitHubUseCase) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> = _repositories

    fun fetchRepositories(page: Int = 1) {
        viewModelScope.launch {
            val repos = useCase.execute(page)
            _repositories.postValue(repos)
        }
    }
}