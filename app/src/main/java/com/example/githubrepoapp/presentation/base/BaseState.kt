package com.example.githubrepoapp.presentation.base
data class BaseState <T>(
    val isLoading:Boolean?=null,
    val errorView: ErrorView?=null,
    val data: T?=null
)