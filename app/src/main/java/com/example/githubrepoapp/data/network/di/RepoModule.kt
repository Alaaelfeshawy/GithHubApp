package com.example.githubrepoapp.data.network.di

import android.content.Context
import com.example.githubrepoapp.data.cache.database.RepositoryPagingSource
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.network.apis.DetailsAPI
import com.example.githubrepoapp.data.network.apis.HomeAPI
import com.example.githubrepoapp.data.network.apis.IssuesApi
import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoService
import com.example.githubrepoapp.data.repo.network.ConnectivityChecker
import com.example.githubrepoapp.data.repo.network.NetworkConnectivityChecker
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
    fun provideHomeModule(homeAPI: HomeAPI, homeDaoService: RepositoriesDaoService, connectivityChecker: ConnectivityChecker
    ): IHomeRepo {
        return HomeRepoImpl(homeAPI,homeDaoService ,connectivityChecker)
    }

    @Provides
    fun provideDetailsModule(detailsAPI: DetailsAPI , connectivityChecker: ConnectivityChecker): IRepositoryDetailsRepo {
        return RepositoryDetailsRepoImpl(detailsAPI , connectivityChecker)
    }

    @Provides
    fun provideIssuesModule(issuesApi: IssuesApi, connectivityChecker: ConnectivityChecker): IIssuesRepo {
        return IssuesRepoImpl(issuesApi,connectivityChecker)
    }

    @Provides
    fun provideNetworkConnectivityChecker(context: Context): ConnectivityChecker {
        return NetworkConnectivityChecker(context)
    }
}