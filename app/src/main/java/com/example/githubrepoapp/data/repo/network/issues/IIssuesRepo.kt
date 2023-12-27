package com.example.githubrepoapp.data.repo.network.issues

import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow


interface IIssuesRepo {
     fun getIssues(owner : String,repo : String) : Flow<ApiResult<List<IssueModel>?>>
}