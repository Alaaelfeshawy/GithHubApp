package com.example.githubrepoapp.domain.base

import com.example.githubrepoapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCaseWithParams<T : Any? , params : Any> {

    abstract fun run(params: params): Flow<ApiResult<T>>
}