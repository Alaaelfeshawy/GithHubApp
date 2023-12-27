package com.example.githubrepoapp.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.githubrepoapp.data.cache.models.RepositoriesCacheEntity
import com.example.githubrepoapp.data.network.models.RepositoryModel

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories LIMIT :limit OFFSET :offset ")
    suspend fun getAllRepo(limit: Int, offset: Int): List<RepositoriesCacheEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(model: List<RepositoriesCacheEntity>):LongArray

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(model: RepositoriesCacheEntity):Int

}