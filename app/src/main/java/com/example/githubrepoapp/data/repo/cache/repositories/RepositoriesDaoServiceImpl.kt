package com.example.githubrepoapp.data.repo.cache.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.example.githubrepoapp.data.cache.database.RepositoryPagingSource
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.network.models.RepositoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoriesDaoServiceImpl @Inject constructor(
    private val repositoryDao: RepositoryDao,
    private val repositoriesCacheMapper: RepositoriesCacheMapper,
    private val repositoryPagingSource : RepositoryPagingSource,
    ): RepositoriesDaoService {

    override suspend fun insertRepos(model: List<RepositoryModel>): LongArray {
        return repositoryDao.insertAll(repositoriesCacheMapper.reposListToEntityList(model))
    }

    override suspend fun getReposSizeInDB(): Int? {
        return repositoriesCacheMapper.entityListToRepositoriesList(repositoryDao.getAllRepo()).size
    }

    private fun getRepositoryPagingSource(): PagingSource<Int, RepositoryModel> {
        return RepositoryPagingSource(repositoryDao, repositoriesCacheMapper)
    }

    override fun getRepositoryFromDB() =  Pager(config = PagingConfig(pageSize = 10,
        enablePlaceholders = true),
        pagingSourceFactory = { getRepositoryPagingSource() }
    )

}













