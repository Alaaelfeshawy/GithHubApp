package com.example.githubrepoapp.data.repo.cache.repositories

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.githubrepoapp.data.network.models.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface RepositoriesDaoService {

    suspend fun insertRepos(model: List<RepositoryModel>): LongArray

    suspend fun getReposSizeInDB(): Int?
    fun getRepositoryFromDB(): Pager<Int, RepositoryModel>
}












