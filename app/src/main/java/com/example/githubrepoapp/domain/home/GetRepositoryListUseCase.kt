package com.example.githubrepoapp.domain.home

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.repo.network.home.IHomeRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryListUseCase @Inject constructor(private val homeRepoImpl: IHomeRepo)
    : BaseUseCase<Pager<Int, RepositoryModel>>()
{
    override fun run():  Flow<ApiResult<Pager<Int, RepositoryModel>>> {
        return homeRepoImpl.getRepositoryList()
    }
}