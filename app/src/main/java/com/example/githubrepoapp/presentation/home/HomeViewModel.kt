package com.example.githubrepoapp.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.di.IoDispatcher
import com.example.githubrepoapp.domain.home.GetRepositoryListUseCase
import com.example.githubrepoapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRepositoryListUseCase: GetRepositoryListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : BaseViewModel<Flow<PagingData<RepositoryModel>>>(){

    fun getRepositories() {
        _state.value = _state.value.copy(isLoading = true)
        getRepositoryListUseCase.run().onEach{
            when (it) {
                is ApiResult.Error -> {
                    errorHandler(it)
                }

                is ApiResult.Success -> {
                    val result = it.value.flow.cachedIn(viewModelScope)
                    _state.value = _state.value.copy(isLoading = false , data = result)
                }
            }
        }.flowOn(dispatcher).launchIn(viewModelScope)
    }
}