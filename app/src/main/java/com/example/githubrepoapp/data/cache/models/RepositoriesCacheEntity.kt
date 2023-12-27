package com.example.githubrepoapp.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.githubrepoapp.data.cache.database.DataConverter

@Entity(tableName = "repositories")
data class RepositoriesCacheEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?=null,
    @ColumnInfo(name = "description")
    val description: String?=null,
    @ColumnInfo(name = "name")
    val name: String?=null,
    @ColumnInfo(name = "owner")
    @TypeConverters(DataConverter::class)
    val owner: OwnerCacheEntity?=null,
)