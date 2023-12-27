package com.example.githubrepoapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.di.IoDispatcher
import com.example.githubrepoapp.domain.detail.GetRepositoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getRepositoryDetailsUseCase: GetRepositoryDetailsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(){

    private var _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    fun getRepositoryDetails(owner:String , repo : String){
        _state.value = _state.value.copy(isLoading = true)
        getRepositoryDetailsUseCase.run(
            GetRepositoryDetailsUseCase.Params(owner, repo)
        ).onEach {
            when(it){
                    is ApiResult.Error -> {
                        when (it.errorStatus) {
                            ErrorStatus.TIMEOUT -> _state.value = state.value.copy(
                                isLoading = false,
                                errorMessage = it.errorMessage ?: "Please Try later"
                            )

                            ErrorStatus.NO_CONNECTION -> _state.value = state.value.copy(
                                isLoading = false,
                                errorMessage = it.errorMessage ?: "No internet connection"
                            )

                            ErrorStatus.UNKNOWN_ERROR -> _state.value = state.value.copy(
                                isLoading = false,
                                errorMessage = it.errorMessage ?: "Unknown Error"
                            )

                            ErrorStatus.ERROR -> _state.value = state.value.copy(
                                isLoading = false,
                                errorMessage = it.errorMessage ?: "Unknown Error"
                            )

                            null -> _state.value = state.value.copy(
                                isLoading = false,
                                errorMessage = it.errorMessage ?: "Unknown Error"
                            )
                        }
                    }

                    is ApiResult.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            data = it.value
                        )
                    }
            }
        }.flowOn(dispatcher).launchIn(viewModelScope)
    }
}