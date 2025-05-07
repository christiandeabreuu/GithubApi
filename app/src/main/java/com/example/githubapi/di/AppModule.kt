package com.example.githubapi.di

import com.example.githubapi.data.GitHubService
import org.koin.dsl.module
import com.example.githubapi.data.RepoGitHubRepository
import com.example.githubapi.domain.RepoGitHubUseCase
import com.example.githubapi.ui.HomeViewModel
import com.example.githubapi.data.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

//    // Retrofit Client
//    single { RetrofitClient.instance }

    // Repository
    factory { RepoGitHubRepository(get()) }

    // Use Case
    factory { RepoGitHubUseCase(get()) }

    // ViewModel
    viewModel { HomeViewModel(get()) }
}

val networkModule = module {
    single {
        Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(GitHubService::class.java)
    }
}