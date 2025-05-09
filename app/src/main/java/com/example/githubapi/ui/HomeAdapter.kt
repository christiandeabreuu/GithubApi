package com.example.githubapi.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubapi.R
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.databinding.ItemRepositoryBinding

class RepositoryAdapter(private val context: Context) :
    PagingDataAdapter<GitHubRepo, RepositoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var failedImageCount = 0

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GitHubRepo>() {
            override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, context, this)
    }

    class ViewHolder(
        private val binding: ItemRepositoryBinding,
        private val context: Context,
        private val adapter: RepositoryAdapter
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(repo: GitHubRepo?) {
            binding.repoName.text = repo?.name
            binding.repoStars.text = "⭐ ${repo?.stargazers_count}"
            binding.repoForks.text = "🔁 ${repo?.forks_count}"
            binding.ownerName.text = repo?.owner?.login

            binding.ownerImage.load(repo?.owner?.avatar_url) {
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_foreground)
                listener(onError = { _, _ ->
                    adapter.failedImageCount++

                    if (adapter.failedImageCount == adapter.itemCount) {
                        Toast.makeText(context, "Nenhuma imagem carregada!", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }
}