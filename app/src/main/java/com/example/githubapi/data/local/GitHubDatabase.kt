package com.example.githubapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        @Volatile private var INSTANCE: GitHubDatabase? = null

        fun getInstance(context: Context): GitHubDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GitHubDatabase::class.java,
                    "github_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}