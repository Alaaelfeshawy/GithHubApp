package com.example.githubrepoapp.presentation.base

sealed interface ErrorView
data class ErrorToast (val errorMessage: String?): ErrorView
data class ErrorFullScreen(val errorMessage: String?) : ErrorView
data class ErrorItem(val errorMessage: String?) : ErrorView

data class ErrorNetwork(val errorMessage: String?) : ErrorView

object EmptyData: ErrorView