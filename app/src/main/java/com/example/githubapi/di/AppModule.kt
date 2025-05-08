package com.example.githubapi.di

import androidx.room.Room
import com.example.githubapi.data.local.GitHubDatabase
import com.example.githubapi.data.network.GitHubService
import org.koin.dsl.module
import com.example.githubapi.data.model.RepoGitHubRepository
import com.example.githubapi.domain.RepoGitHubUseCase
import com.example.githubapi.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Repository
    factory { RepoGitHubRepository(get(), get()) }

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

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(), GitHubDatabase::class.java, "github_database"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<GitHubDatabase>().repositoryDao() }
}