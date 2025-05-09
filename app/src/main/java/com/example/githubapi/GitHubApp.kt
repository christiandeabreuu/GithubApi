package com.example.githubapi

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import com.example.githubapi.di.appModule
import com.example.githubapi.di.databaseModule
import com.example.githubapi.di.networkModule

class GitHubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GitHubApp)
            modules(appModule, networkModule, databaseModule)
        }
    }
}