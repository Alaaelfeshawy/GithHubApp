package com.example.githubrepoapp.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.githubrepoapp.data.cache.database.DataConverter
import com.example.githubrepoapp.data.network.models.OwnerModel

@Entity(tableName = "repo_details")
data class RepoDetailCacheEntity (
    @ColumnInfo(name = "description")
    val description: String?=null,
    @ColumnInfo(name = "forks_count")
    val forks_count: Int?=null,
    @ColumnInfo(name = "full_name")
    val full_name: String?=null,
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?=null,
    @ColumnInfo(name = "name")
    val name: String?=null,
    @ColumnInfo(name = "open_issues")
    val open_issues: Int?=null,
    @ColumnInfo(name = "open_issues_count")
    val open_issues_count: Int?=null,
    @ColumnInfo(name = "owner")
    @TypeConverters(DataConverter::class)
    val owner: OwnerCacheEntity?=null,
    @ColumnInfo(name = "pushed_at")
    val pushed_at: String?=null,
    @ColumnInfo(name = "stargazers_count")
    val stargazers_count: Int?=null,
    @ColumnInfo(name = "updated_at")
    val updated_at: String?=null,
    @ColumnInfo(name = "watchers")
    val watchers: Int?=null,
    @ColumnInfo(name = "watchers_count")
    val watchers_count: Int?=null,
)