package com.example.githubapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubapi.R
import com.example.githubapi.data.Repository

class HomeAdapter : ListAdapter<Repository, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }

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
        val repo = getItem(position)
        holder.name.text = repo.name
        holder.stars.text = "⭐ ${repo.stargazers_count}"
        holder.forks.text = "🔁 ${repo.forks_count}"
        holder.ownerName.text = repo.owner.login

        holder.ownerImage.load(repo.owner.avatar_url) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
        }

    }
}