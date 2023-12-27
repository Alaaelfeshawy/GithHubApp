package com.example.githubrepoapp.domain.home

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.repo.network.home.IHomeRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryListUseCase @Inject constructor(private val homeRepoImpl: IHomeRepo)
    : BaseUseCase<Boolean>()
{
    override fun run():  Flow<ApiResult<Boolean>> {
        return homeRepoImpl.getRepositoryList()
    }

    fun getReposFromDb(): Flow<PagingData<RepositoryModel>> = homeRepoImpl.getRepositoryFromDB()
    suspend fun getReposSizeInDB(): Int? = homeRepoImpl.getReposSizeInDB()

}