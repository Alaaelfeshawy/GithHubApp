package com.example.githubrepoapp.domain.home

import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.repo.network.home.IHomeRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryListUseCase @Inject constructor(private val homeRepoImpl: IHomeRepo)
    : BaseUseCase<List<RepositoryModel>?>()
{
    override fun run(): Flow<ApiResult<List<RepositoryModel>?>> {
        return homeRepoImpl.getRepositoryList()
    }

}