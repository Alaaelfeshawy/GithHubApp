package com.example.githubrepoapp.data.network.apis

import com.example.githubrepoapp.constants.Constant.NetworkConstant.REPOSITORIES
import com.example.githubrepoapp.data.network.models.RepositoryModel
import retrofit2.Response
import retrofit2.http.GET

interface HomeAPI {
    @GET(REPOSITORIES)
    suspend fun getRepositoryList() : Response<List<RepositoryModel>>

}