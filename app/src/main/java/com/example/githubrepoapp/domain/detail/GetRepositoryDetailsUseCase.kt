package com.example.githubrepoapp.domain.detail

import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.repo.network.details.IRepositoryDetailsRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.base.BaseUseCaseWithParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryDetailsUseCase @Inject constructor(
    private val repoDetails: IRepositoryDetailsRepo
) : BaseUseCaseWithParams<RepositoryDetailsModel?,GetRepositoryDetailsUseCase.Params>() {
    data class Params (
         val owner : String,
         val repo : String
    )

    override  fun run(params: Params): Flow<ApiResult<RepositoryDetailsModel?>> {
        return repoDetails.getRepositoryDetails(params.owner , params.repo)
    }
}