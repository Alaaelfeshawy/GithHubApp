package com.example.githubrepoapp.data.utils

import com.example.githubrepoapp.constants.Constant.DataConstant.NO_CONNECTION_CODE
import com.example.githubrepoapp.constants.Constant.DataConstant.TIME_OUT_CODE
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

sealed class ApiResult<out T> {

    data class Success<out T>(val value: T): ApiResult<T>()

    data class Error(val code: Int? = null, val errorMessage: String? = null , val errorStatus: ErrorStatus?=null): ApiResult<Nothing>()

}

suspend fun <T : Any> safeApiCall(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else if( response.code() != 200){
            val errorBody = response.errorBody()?.string()
            if (errorBody !=null){
                try {
                    val errorResponse = parseGithubApiError(errorBody.toString())
                    val errorMessage = errorResponse.message
                    ApiResult.Error(code = response.code(), errorMessage = errorMessage , ErrorStatus.UNKNOWN_ERROR)
                } catch (e: Exception) {
                    ApiResult.Error(code = response.code(), errorMessage = "Something wrong happened please try later" , ErrorStatus.ERROR)
                }
            }else{
                ApiResult.Error(code = response.code(), errorMessage = "Something wrong happened please try later" , ErrorStatus.ERROR)
            }

        }
        else {
            ApiResult.Error(code = null, errorMessage = "No data found" , ErrorStatus.EMPTY_DATA)
        }
    } catch (throwable: HttpException) {
        ApiResult.Error(code = throwable.code(), errorMessage = throwable.message() , ErrorStatus.ERROR)
    } catch (e: Throwable) {
        ApiResult.Error(code = null , errorMessage = e.message , errorStatus = ErrorStatus.ERROR)
    } catch (s: SocketTimeoutException) {
        ApiResult.Error(code =TIME_OUT_CODE, errorMessage = s.message , errorStatus = ErrorStatus.TIMEOUT)
    }catch (io:IOException){
        ApiResult.Error(code = NO_CONNECTION_CODE, errorMessage = io.message , errorStatus = ErrorStatus.NO_CONNECTION)
    }
}

enum class ErrorStatus {
    TIMEOUT,
    NO_CONNECTION,
    UNKNOWN_ERROR,
    ERROR,
    No_INTERNET_CONNECTION,
    EMPTY_DATA
}

data class GithubApiError(
    val message: String,
    @SerializedName("documentation_url")
    val documentationUrl: String
)

fun parseGithubApiError(errorJson: String): GithubApiError {
    return Gson().fromJson(errorJson, GithubApiError::class.java)
}




















