package com.example.githubrepoapp.data.repo.network.details

import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow


interface IRepositoryDetailsRepo {

    fun getRepositoryDetails(owner:String , repo : String) : Flow<ApiResult<RepositoryDetailsModel?>>
}