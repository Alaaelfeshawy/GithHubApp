package com.example.githubrepoapp.presentation.home

import com.example.githubrepoapp.data.network.models.RepositoryModel


data class HomeState  (
     val isLoading :Boolean?=null,
     val errorMessage :String?=null,
     val data :List<RepositoryModel> ?=null
)

data class BaseState<T>  (
     val isLoading :Boolean?=null,
     val errorMessage :String?=null,
     val data :T ?=null
)