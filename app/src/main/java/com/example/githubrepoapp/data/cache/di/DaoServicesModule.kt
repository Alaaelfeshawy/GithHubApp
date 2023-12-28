package com.example.githubrepoapp.data.cache.di

import com.example.githubrepoapp.data.cache.database.RepositoryPagingSource
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoService
import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DaoServicesModule {
    @Singleton
    @Provides
    fun provideRepositoryDaoService(
        repositoryDao: RepositoryDao,
        repositoriesCacheMapper: RepositoriesCacheMapper,
        repositoryPagingSource : RepositoryPagingSource,
        ): RepositoriesDaoService {
        return RepositoriesDaoServiceImpl(repositoryDao, repositoriesCacheMapper,repositoryPagingSource)
    }
}