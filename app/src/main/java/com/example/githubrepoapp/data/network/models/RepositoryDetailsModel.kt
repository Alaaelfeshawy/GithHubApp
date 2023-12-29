package com.example.githubrepoapp.data.network.models

import androidx.paging.NullPaddedList

data class RepositoryDetailsModel(
    val description: String?=null,
    val forks_count: Int?=null,
    val full_name: String?=null,
    val id: Int?=null,
    val name: String?=null,
    val open_issues: Int?=null,
    val open_issues_count: Int?=null,
    val owner: OwnerModel?=null,
    val pushed_at: String?=null,
    val stargazers_count: Int?=null,
    val updated_at: String?=null,
    val watchers: Int?=null,
    val watchers_count: Int?=null,
    val avatar_url : String?=null
)

