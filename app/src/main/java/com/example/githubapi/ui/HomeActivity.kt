package com.example.githubapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupRecyclerView()
        setupObservers()
        viewModel.getRepositories() // 🔥 Chamamos para buscar os dados
    }

    private fun setupRecyclerView() {
        adapter = RepositoryAdapter()
        findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = this@HomeActivity.adapter
        }
    }

    private fun setupObservers() {
        viewModel.repositories.observe(this) { repositories ->
            adapter.updateData(repositories) // 🔥 Atualiza o adapter com uma lista normal
        }
    }
}