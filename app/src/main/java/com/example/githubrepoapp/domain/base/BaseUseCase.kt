package com.example.githubrepoapp.domain.base

import com.example.githubrepoapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T : Any?>() {

    abstract fun run(): Flow<ApiResult<T>>
}