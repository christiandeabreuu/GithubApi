package com.example.githubapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.githubapi.data.Repository
import com.example.githubapi.data.RetrofitClient

class MainViewModel : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> = _repositories

    fun fetchRepositories(page: Int = 1) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getTopRepositories("language:kotlin", "stars", page)
                _repositories.postValue(response.items)
            } catch (e: Exception) {
                // Tratar erro e logar caso necessário
            }
        }
    }
}