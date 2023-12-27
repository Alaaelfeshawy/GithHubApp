package com.example.githubrepoapp.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owner")
data class OwnerCacheEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?=null,
    @ColumnInfo(name = "login")
    val login: String?=null,
    @ColumnInfo(name = "starred_url")
    val starred_url: String?=null,
    @ColumnInfo(name = "type")
    val type: String?=null,
    @ColumnInfo(name = "url")
    val url: String?=null,
)

