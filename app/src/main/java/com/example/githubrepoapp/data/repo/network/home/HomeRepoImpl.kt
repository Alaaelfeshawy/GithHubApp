package com.example.githubrepoapp.data.repo.network.home

import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoService
import com.example.githubrepoapp.data.network.apis.HomeAPI
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val homeAPI: HomeAPI,
    private val homeDaoService: RepositoriesDaoService,
) : IHomeRepo {

    override fun getRepositoryList(): Flow<ApiResult<List<RepositoryModel>?>> {
        return  flow {
            if (getAllRepos().isNullOrEmpty()) {
                safeApiCall {homeAPI.getRepositoryList()}.let {
                    when (it) {
                        is ApiResult.Error -> { emit(it) }
                        is ApiResult.Success -> {
                            homeDaoService.insertRepos(it.value)
                            emit(ApiResult.Success(homeDaoService.getAllRepos()))
                        }
                    }

                }
            }
            else{
                emit(ApiResult.Success(homeDaoService.getAllRepos()))
            }
        }
    }

    override suspend fun getAllRepos(): List<RepositoryModel>? {
        return homeDaoService.getAllRepos()
    }
}