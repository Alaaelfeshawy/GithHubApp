package com.example.githubrepoapp.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.models.RepositoriesCacheEntity

@TypeConverters(value = [DataConverter::class])
@Database(entities = [RepositoriesCacheEntity::class ], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun repoDao(): RepositoryDao

}