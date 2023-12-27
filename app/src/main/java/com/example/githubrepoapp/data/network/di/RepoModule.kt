package com.example.githubrepoapp.data.network.di

import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.network.apis.DetailsAPI
import com.example.githubrepoapp.data.network.apis.HomeAPI
import com.example.githubrepoapp.data.network.apis.IssuesApi
import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoService
import com.example.githubrepoapp.data.repo.network.details.IRepositoryDetailsRepo
import com.example.githubrepoapp.data.repo.network.details.RepositoryDetailsRepoImpl
import com.example.githubrepoapp.data.repo.network.home.HomeRepoImpl
import com.example.githubrepoapp.data.repo.network.home.IHomeRepo
import com.example.githubrepoapp.data.repo.network.issues.IIssuesRepo
import com.example.githubrepoapp.data.repo.network.issues.IssuesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideHomeModule(homeAPI: HomeAPI, homeDaoService: RepositoriesDaoService,
                           repositoriesCacheMapper: RepositoriesCacheMapper, repositoryDao: RepositoryDao
    ): IHomeRepo {
        return HomeRepoImpl(homeAPI,homeDaoService , repositoriesCacheMapper,repositoryDao)
    }

    @Provides
    fun provideDetailsModule(detailsAPI: DetailsAPI): IRepositoryDetailsRepo {
        return RepositoryDetailsRepoImpl(detailsAPI)
    }

    @Provides
    fun provideIssuesModule(issuesApi: IssuesApi): IIssuesRepo {
        return IssuesRepoImpl(issuesApi)
    }
}