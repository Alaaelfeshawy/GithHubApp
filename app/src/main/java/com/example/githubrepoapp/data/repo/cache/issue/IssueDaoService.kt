package com.example.githubrepoapp.data.repo.cache.issue

import com.example.githubrepoapp.data.network.models.IssueModel
import kotlinx.coroutines.flow.Flow

interface IssueDaoService {

    suspend fun insertIssues(model: List<IssueModel>?): LongArray

    suspend fun updateIssue(model : IssueModel): Int

    suspend fun getAllIssues(): Flow<List<IssueModel>>
}












