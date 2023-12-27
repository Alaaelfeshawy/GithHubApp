package com.example.githubrepoapp.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.githubrepoapp.data.cache.models.RepoDetailCacheEntity
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel

@Dao
interface DetailsDao {

    @Query("SELECT * FROM repo_details")
    fun getRepoDetails(): RepoDetailCacheEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(model: RepoDetailCacheEntity):Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRepoDetail(model: RepoDetailCacheEntity):Int
}