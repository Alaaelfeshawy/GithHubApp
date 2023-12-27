package com.example.githubrepoapp.data.network.models

import com.google.gson.annotations.SerializedName

data class IssueModel(
    @SerializedName("author_association")
    val author: String ?=null,
    @SerializedName("created_at")
    val createdAt: String ?=null,
    val state: String ?=null,
    val title: String?=null,
    val id: Int?=null,
)


