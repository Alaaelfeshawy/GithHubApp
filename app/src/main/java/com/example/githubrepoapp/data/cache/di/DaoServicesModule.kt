package com.example.githubrepoapp.data.cache.di

import com.example.githubrepoapp.data.cache.database.dao.DetailsDao
import com.example.githubrepoapp.data.cache.database.dao.IssuesDao
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.IssueCacheMapper
import com.example.githubrepoapp.data.cache.mappers.RepoDetailsCacheMapper
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.repo.cache.detail.DetailDaoService
import com.example.githubrepoapp.data.repo.cache.detail.DetailDaoServiceImpl
import com.example.githubrepoapp.data.repo.cache.issue.IssueDaoService
import com.example.githubrepoapp.data.repo.cache.issue.IssueDaoServiceImpl
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
    fun provideDetailDaoService(
        detailsDao: DetailsDao,
        repoDetailsCacheMapper: RepoDetailsCacheMapper,
    ): DetailDaoService {
        return DetailDaoServiceImpl(detailsDao, repoDetailsCacheMapper)
    }
    @Singleton
    @Provides
    fun provideIssueDaoService(
        issuesDao: IssuesDao,
        issueCacheMapper: IssueCacheMapper,
    ): IssueDaoService {
        return IssueDaoServiceImpl(issuesDao, issueCacheMapper)
    }

    @Singleton
    @Provides
    fun provideRepositoryDaoService(
        repositoryDao: RepositoryDao,
        repositoriesCacheMapper: RepositoriesCacheMapper,
    ): RepositoriesDaoService {
        return RepositoriesDaoServiceImpl(repositoryDao, repositoriesCacheMapper)
    }
}