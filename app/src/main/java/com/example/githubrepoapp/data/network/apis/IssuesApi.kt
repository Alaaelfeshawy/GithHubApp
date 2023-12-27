package com.example.githubrepoapp.data.network.apis

import com.example.githubrepoapp.constants.Constant.NetworkConstant.ISSUES
import com.example.githubrepoapp.constants.Constant.NetworkParamConstant.OWNER
import com.example.githubrepoapp.constants.Constant.NetworkParamConstant.REPO
import com.example.githubrepoapp.data.network.models.IssueModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IssuesApi {

    @GET(ISSUES)
    suspend fun getIssuesList(
        @Path(OWNER) owner : String, @Path(REPO) repo : String
    ) : Response<List<IssueModel>>
}