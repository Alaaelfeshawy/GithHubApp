package com.example.githubrepoapp.data.repo.network.home

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow


interface IHomeRepo {

    fun getRepositoryList() :  Flow<ApiResult<Boolean>>

    fun getRepositoryFromDB(): Flow<PagingData<RepositoryModel>>
    suspend fun getReposSizeInDB(): Int?
}