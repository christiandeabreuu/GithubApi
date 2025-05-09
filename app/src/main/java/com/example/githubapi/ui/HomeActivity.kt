package com.example.githubapi.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapi.databinding.ActivityHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: RepositoryAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        setupRecyclerView()
        setupObservers()

    }

    private fun setupRecyclerView() {
        adapter = RepositoryAdapter(context = this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = this@HomeActivity.adapter
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.repositories.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}