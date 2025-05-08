package com.example.githubapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubapi.R
import com.example.githubapi.data.model.GitHubRepo

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositories: List<GitHubRepo> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.repo_name)
        val stars: TextView = view.findViewById(R.id.repo_stars)
        val forks: TextView = view.findViewById(R.id.repo_forks)
        val ownerImage: ImageView = view.findViewById(R.id.owner_image)
        val ownerName: TextView = view.findViewById(R.id.owner_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repositories[position] // 🔥 Agora usamos uma lista normal
        holder.name.text = repo.name
        holder.stars.text = "⭐ ${repo.stargazers_count}"
        holder.forks.text = "🔁 ${repo.forks_count}"
        holder.ownerName.text = repo.owner.login

        holder.ownerImage.load(repo.owner.avatar_url) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    override fun getItemCount(): Int = repositories.size

    fun updateData(newData: List<GitHubRepo>) { // 🔥 Método para atualizar a lista
        repositories = newData
        notifyDataSetChanged()
    }
}