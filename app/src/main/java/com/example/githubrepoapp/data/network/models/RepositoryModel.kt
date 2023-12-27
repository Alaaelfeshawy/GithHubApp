package com.example.githubrepoapp.data.network.models

//todo add STAR COUNT
data class RepositoryModel(
    val id: Int?=null,
    val description: String?=null,
    val name: String?=null,
    val owner: OwnerModel?=null,
)

