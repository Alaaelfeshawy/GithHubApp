package com.example.githubrepoapp.data.repo.cache.repositories

import com.example.githubrepoapp.data.network.models.RepositoryModel

interface RepositoriesDaoService {

    suspend fun insertRepos(model: List<RepositoryModel>): LongArray

    suspend fun updateRepo(model : RepositoryModel): Int

    suspend fun getAllRepos(): List<RepositoryModel>?
}












