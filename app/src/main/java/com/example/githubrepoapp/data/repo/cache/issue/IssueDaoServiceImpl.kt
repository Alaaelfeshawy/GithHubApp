package com.example.githubrepoapp.data.repo.cache.issue

import com.example.githubrepoapp.data.cache.database.dao.IssuesDao
import com.example.githubrepoapp.data.cache.mappers.IssueCacheMapper
import com.example.githubrepoapp.data.network.models.IssueModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IssueDaoServiceImpl @Inject constructor(
    private val issuesDao: IssuesDao,
    private val issueCacheMapper: IssueCacheMapper, ): IssueDaoService {

    override suspend fun insertIssues(model: List<IssueModel>?): LongArray {
        return issuesDao.insertAllIssues(issueCacheMapper.issueListToEntityList(model))
    }

    override suspend fun updateIssue(model: IssueModel): Int {
        return issuesDao.updateIssue(issueCacheMapper.mapToEntity(model))
    }

    override suspend fun getAllIssues(): Flow<List<IssueModel>> {
        return flow { emit(issueCacheMapper.entityListToIssueList(issuesDao.getAllIssues())) }
    }
}













