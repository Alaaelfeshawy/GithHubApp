package com.example.githubrepoapp.data.repo.network.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.example.githubrepoapp.data.cache.database.RepositoryPagingSource
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.network.apis.HomeAPI
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoService
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val homeAPI: HomeAPI,
    private val homeDaoService: RepositoriesDaoService,
    private val repositoriesCacheMapper: RepositoriesCacheMapper,
    private val repositoryDao: RepositoryDao
    ) : IHomeRepo {

    override fun getRepositoryList(): Flow<ApiResult<Boolean>> {
        return  flow {
                safeApiCall { homeAPI.getRepositoryList() }.let {
                    when (it) {
                        is ApiResult.Error -> {
                            emit(it)
                        }

                        is ApiResult.Success -> {
                            homeDaoService.insertRepos(it.value)
                            emit(ApiResult.Success(true))
                        }
                    }

                }
        }
    }

    private fun getRepositoryPagingSource(): PagingSource<Int, RepositoryModel> {
        return RepositoryPagingSource(repositoryDao,repositoriesCacheMapper)
    }
    override suspend fun getReposSizeInDB():Int?{
       return homeDaoService.getReposSizeInDB()
    }

    override fun getRepositoryFromDB() =  Pager(config = PagingConfig(pageSize = 10,
        enablePlaceholders = true),
        pagingSourceFactory = {
            getRepositoryPagingSource()
        }
    ).flow
}