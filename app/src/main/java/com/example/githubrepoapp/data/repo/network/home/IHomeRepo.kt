package com.example.githubrepoapp.data.repo.network.home

import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow


interface IHomeRepo {

    fun getRepositoryList() : Flow<ApiResult<List<RepositoryModel>?>>

    suspend fun getAllRepos():List<RepositoryModel>?
}