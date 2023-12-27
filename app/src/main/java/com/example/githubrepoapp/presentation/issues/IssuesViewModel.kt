package com.example.githubrepoapp.presentation.issues
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.di.IoDispatcher
import com.example.githubrepoapp.domain.issue.GetIssuesUseCase
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
class IssuesViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ) : ViewModel(){

    private var _state = MutableStateFlow(IssuesState())
    val state = _state.asStateFlow()

    fun getIssues(owner : String , repo : String){
        getIssuesUseCase.run(GetIssuesUseCase.Params(owner, repo))
            .onStart {
                _state.value=_state.value.copy(isLoading = true)
            }
            .onEach {
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

                            null -> _state.value = state.value.copy(isLoading = false, errorMessage = it.errorMessage ?: "Unknown Error")
                        }
                    }

                    is ApiResult.Success -> {
                        _state.value = state.value.copy(isLoading = false, data = it.value)
                    }
                }
            }.flowOn(dispatcher).launchIn(viewModelScope)

    }

}