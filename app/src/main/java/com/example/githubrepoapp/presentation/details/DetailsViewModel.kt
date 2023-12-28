package com.example.githubrepoapp.presentation.details

import androidx.lifecycle.viewModelScope
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.di.IoDispatcher
import com.example.githubrepoapp.domain.detail.GetRepositoryDetailsUseCase
import com.example.githubrepoapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getRepositoryDetailsUseCase: GetRepositoryDetailsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<RepositoryDetailsModel>(){

    fun getRepositoryDetails(owner:String , repo : String){
        _state.value = _state.value.copy(isLoading = true)
        getRepositoryDetailsUseCase.run(
            GetRepositoryDetailsUseCase.Params(owner, repo)
        ).onEach {
            when(it){
                    is ApiResult.Error -> {
                        errorHandler(it)
                    }

                    is ApiResult.Success -> { _state.value = state.value.copy(isLoading = false,
                        data = it.value)
                    }
            }
        }.flowOn(dispatcher).launchIn(viewModelScope)
    }
}