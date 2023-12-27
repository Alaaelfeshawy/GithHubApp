package com.example.githubrepoapp.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "issues")
data class IssuesCacheEntity (
    @ColumnInfo(name = "author")
    val author: String?=null,
    @ColumnInfo(name = "createdAt")
    val createdAt: String?=null,
    @ColumnInfo(name = "state")
    val state: String?=null,
    @ColumnInfo(name = "title")
    val title: String?=null,
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?=null,
)