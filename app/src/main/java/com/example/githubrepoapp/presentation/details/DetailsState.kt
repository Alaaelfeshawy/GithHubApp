package com.example.githubrepoapp.presentation.details

import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel


data class DetailsState  (
     val isLoading :Boolean?=null,
     val errorMessage :String?=null,
     val data : RepositoryDetailsModel?=null
)