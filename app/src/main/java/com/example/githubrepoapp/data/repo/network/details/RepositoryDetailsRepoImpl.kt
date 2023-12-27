package com.example.githubrepoapp.data.repo.network.details

import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.network.apis.DetailsAPI
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryDetailsRepoImpl  @Inject constructor(
    private val detailsAPI: DetailsAPI
) : IRepositoryDetailsRepo {

    override fun getRepositoryDetails(owner:String , repo : String): Flow<ApiResult<RepositoryDetailsModel?>> {
        return  flow {
            emit(safeApiCall { detailsAPI.getRepositoryDetails(owner, repo) })
        }
    }
}