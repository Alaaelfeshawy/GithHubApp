package com.example.githubrepoapp.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubrepoapp.data.cache.database.dao.DetailsDao
import com.example.githubrepoapp.data.cache.database.dao.IssuesDao
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.models.IssuesCacheEntity
import com.example.githubrepoapp.data.cache.models.RepoDetailCacheEntity
import com.example.githubrepoapp.data.cache.models.RepositoriesCacheEntity

@TypeConverters(value = [DataConverter::class])
@Database(entities = [RepoDetailCacheEntity::class , IssuesCacheEntity::class , RepositoriesCacheEntity::class ], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun detailsDao(): DetailsDao
    abstract fun issuesDao(): IssuesDao
    abstract fun repoDao(): RepositoryDao

}