package com.example.githubrepoapp.domain.issue

import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.data.repo.network.issues.IIssuesRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.base.BaseUseCaseWithParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val issuesRepo: IIssuesRepo,
): BaseUseCaseWithParams<List<IssueModel>?, GetIssuesUseCase.Params>() {
    data class Params (
         val owner : String,
         val repo : String
    )
     override fun run (params : Params) : Flow<ApiResult<List<IssueModel>?>> {
        return issuesRepo.getIssues(params.owner , params.repo)
    }
}