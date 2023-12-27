package com.example.githubrepoapp.data.repo.cache.detail
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel

interface DetailDaoService {

    suspend fun insertDetail(model: RepositoryDetailsModel): Long

    suspend fun updateDetail(model : RepositoryDetailsModel): Int

    suspend fun getDetail(): RepositoryDetailsModel?
}












