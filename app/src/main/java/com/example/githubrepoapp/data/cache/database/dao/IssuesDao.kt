package com.example.githubrepoapp.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.githubrepoapp.data.cache.mappers.IssueCacheMapper
import com.example.githubrepoapp.data.cache.models.IssuesCacheEntity
import com.example.githubrepoapp.data.network.models.IssueModel

@Dao
interface IssuesDao {

    @Query("SELECT * FROM issues")
    suspend fun getAllIssues(): List<IssuesCacheEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllIssues(model: List<IssuesCacheEntity>) : LongArray

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateIssue(model: IssuesCacheEntity):Int
}