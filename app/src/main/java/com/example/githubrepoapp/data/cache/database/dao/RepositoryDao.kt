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

    @Query("SELECT * FROM repositories")
    suspend fun getAllRepo(): List<RepositoriesCacheEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(model: List<RepositoriesCacheEntity>):LongArray

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(model: RepositoriesCacheEntity):Int

}