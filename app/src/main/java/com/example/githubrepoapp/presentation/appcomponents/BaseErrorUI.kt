package com.example.githubrepoapp.presentation.appcomponents

import androidx.compose.runtime.Composable
import com.example.githubrepoapp.presentation.base.EmptyData
import com.example.githubrepoapp.presentation.base.ErrorFullScreen
import com.example.githubrepoapp.presentation.base.ErrorItem
import com.example.githubrepoapp.presentation.base.ErrorNetwork
import com.example.githubrepoapp.presentation.base.ErrorToast
import com.example.githubrepoapp.presentation.base.ErrorView

@Composable
fun BaseErrorUI(errorView: ErrorView) {
    when(errorView){
        is ErrorFullScreen -> ErrorFullScreenUI(message = errorView.errorMessage ?: "")
        is ErrorItem -> ErrorItem(message =errorView.errorMessage ?: "")
        is ErrorToast -> GenericSnackbar(message = "Message: ${errorView.errorMessage}", actionText = "Dismiss",)
        is ErrorNetwork -> NetworkErrorUI(message = errorView.errorMessage ?: "")
        is EmptyData -> EmptyScreen()
    }
}
