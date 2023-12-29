package com.example.githubrepoapp.presentation.base

import androidx.lifecycle.ViewModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<T> :ViewModel() {

    protected var _state = MutableStateFlow(BaseState<T>())
    val state = _state.asStateFlow()

    fun errorHandler(error: ApiResult.Error){
        when (error.errorStatus) {
            ErrorStatus.TIMEOUT -> _state.value = state.value.copy(
                isLoading = false,
                errorView = ErrorToast(error.errorMessage ?: "Please Try later")
            )

            ErrorStatus.NO_CONNECTION -> _state.value = state.value.copy(
                isLoading = false,
                errorView = ErrorToast(error.errorMessage ?: "No internet connection")
            )

            ErrorStatus.UNKNOWN_ERROR -> _state.value = state.value.copy(
                isLoading = false,
                errorView = ErrorFullScreen(error.errorMessage ?: "Unknown Error")
            )

            ErrorStatus.ERROR -> _state.value = state.value.copy(
                isLoading = false,
                errorView = ErrorToast(error.errorMessage ?: "Unknown Error")
            )
            ErrorStatus.No_INTERNET_CONNECTION -> _state.value = state.value.copy(
                isLoading = false,
                errorView = ErrorNetwork(error.errorMessage ?: "No internet connection")
            )

            ErrorStatus.EMPTY_DATA -> _state.value = state.value.copy(
                isLoading = false,
                errorView = EmptyData
            )

            null -> _state.value = state.value.copy(
                isLoading = false,
                errorView = ErrorToast(error.errorMessage ?: "Unknown Error")
            )

        }
    }

}

