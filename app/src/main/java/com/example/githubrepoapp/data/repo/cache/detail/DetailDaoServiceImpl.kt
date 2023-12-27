package com.example.githubrepoapp.data.repo.cache.detail
import com.example.githubrepoapp.data.cache.database.dao.DetailsDao
import com.example.githubrepoapp.data.cache.mappers.RepoDetailsCacheMapper
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailDaoServiceImpl @Inject constructor(
    private val detailsDao: DetailsDao,
    private val repoDetailsCacheMapper: RepoDetailsCacheMapper, ): DetailDaoService {


    override suspend fun insertDetail(model: RepositoryDetailsModel): Long {
        return detailsDao.insertDetail(repoDetailsCacheMapper.mapToEntity(model))
    }

    override suspend fun updateDetail(model: RepositoryDetailsModel): Int {
        return detailsDao.updateRepoDetail(repoDetailsCacheMapper.mapToEntity(model))
    }

    override suspend fun getDetail(): RepositoryDetailsModel? {
        return detailsDao.getRepoDetails()?.let { repoDetailsCacheMapper.mapFromEntity(it) }
    }
}













