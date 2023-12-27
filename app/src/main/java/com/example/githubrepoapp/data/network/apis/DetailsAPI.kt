package com.example.githubrepoapp.data.network.apis

import com.example.githubrepoapp.constants.Constant.NetworkConstant.GET_REPO_DETAILS
import com.example.githubrepoapp.constants.Constant.NetworkParamConstant.OWNER
import com.example.githubrepoapp.constants.Constant.NetworkParamConstant.REPO
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsAPI {

    @GET(GET_REPO_DETAILS)
    suspend fun getRepositoryDetails(
       @Path(OWNER) owner : String ,  @Path(REPO) repo : String
    ) : Response<RepositoryDetailsModel>
}