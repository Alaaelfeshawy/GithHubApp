package com.example.githubrepoapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.di.IoDispatcher
import com.example.githubrepoapp.domain.home.GetRepositoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRepositoryListUseCase: GetRepositoryListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel(){

    private var _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getRepo()
    }
   fun getRepo(){
       viewModelScope.launch {
           if (getRepositoryListUseCase.getReposSizeInDB()!! > 0){
               getReposFromDb()
           }else{
               getRepositories()
           }
       }
   }
    private fun getRepositories() {
        viewModelScope.launch {
            getRepositoryListUseCase.run().collect{
                when (it) {
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
                        if (it.value){
                            getReposFromDb()
                        }
                    }
                }
            }
        }
    }

     private fun getReposFromDb() {
         val result = getRepositoryListUseCase.getReposFromDb().cachedIn(viewModelScope)
         _state.value = _state.value.copy(isLoading = false , data = result)
     }
}