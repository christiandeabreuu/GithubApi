package com.example.githubapi.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepositoryEntity::class], version = 1, exportSchema = false)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}