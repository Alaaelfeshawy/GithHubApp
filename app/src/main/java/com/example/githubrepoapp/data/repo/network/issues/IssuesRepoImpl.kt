package com.example.githubrepoapp.data.repo.network.issues

import com.example.githubrepoapp.data.network.apis.IssuesApi
import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.data.repo.network.ConnectivityChecker
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.data.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IssuesRepoImpl @Inject constructor(
    private val issuesApi: IssuesApi,
    private val connectivityChecker: ConnectivityChecker
) : IIssuesRepo {

    override fun getIssues( owner : String,repo : String): Flow<ApiResult<List<IssueModel>?>> {
        return  flow {
            if (!connectivityChecker.isConnected()) {
                emit(
                    ApiResult.Error(
                        errorMessage = "No Internet Connection",
                        errorStatus = ErrorStatus.No_INTERNET_CONNECTION
                    )
                )
            } else {
                emit(safeApiCall { issuesApi.getIssuesList(owner, repo) })
            }
        }
    }
}

