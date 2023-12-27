package com.example.githubrepoapp.presentation.home

import androidx.paging.PagingData
import com.example.githubrepoapp.data.network.models.RepositoryModel
import kotlinx.coroutines.flow.Flow


data class HomeState(
    val isLoading:Boolean?=null,
    val errorMessage:String?=null,
    val data: Flow<PagingData<RepositoryModel>>?=null
)

data class BaseState<T>  (
     val isLoading :Boolean?=null,
     val errorMessage :String?=null,
     val data :T ?=null
)