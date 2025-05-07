package com.example.githubapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.data.RepoGitHubRepository
import com.example.githubapi.data.RetrofitClient
import com.example.githubapi.domain.RepoGitHubUseCase

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val repository = RepoGitHubRepository(RetrofitClient.instance)
        val useCase = RepoGitHubUseCase(repository)

        val factory = HomeViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        adapter = RepositoryAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.repositories.observe(this) { list ->
            adapter.submitList(list)
        }

        viewModel.fetchRepositories()
    }
}