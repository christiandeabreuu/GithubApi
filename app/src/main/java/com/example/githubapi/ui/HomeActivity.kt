package com.example.githubapi.ui

import android.os.Bundle
import android.util.Log
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("Lifecycle", "HomeActivity foi recriada!")


        adapter = RepositoryAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getRepositories().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}