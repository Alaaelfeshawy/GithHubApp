package com.example.githubrepoapp.data.repo

import com.example.githubrepoapp.data.network.apis.DetailsAPI
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.repo.network.ConnectivityChecker
import com.example.githubrepoapp.data.repo.network.details.RepositoryDetailsRepoImpl
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DetailsRepoTest : BaseUnitTest() {
    private val detailsAPI: DetailsAPI = mockk()
    private val connectivityChecker: ConnectivityChecker = mockk()
    private val mockResponse= mockk<RepositoryDetailsModel>()
    private lateinit var SUT: RepositoryDetailsRepoImpl

    @Before
    fun before(){
        SUT = RepositoryDetailsRepoImpl(detailsAPI, connectivityChecker)
    }

    @Test
    fun `getRepositoryDetails from api when network is connected then should return success with data`() = runTest {
        coEvery { detailsAPI.getRepositoryDetails("owner","repo")} returns Response.success(mockResponse)

        coEvery { connectivityChecker.isConnected()} returns true

        val result = SUT.getRepositoryDetails("owner","repo").first()

        advanceUntilIdle()

        TestCase.assertEquals(ApiResult.Success(mockResponse), result)
    }

    @Test
    fun `getRepositoryDetails from api when and network is connected then should return success with no data`() = runTest {
        coEvery { detailsAPI.getRepositoryDetails("owner","repo")} returns Response.success(null)

        coEvery { connectivityChecker.isConnected()} returns true


        val result = SUT.getRepositoryDetails("owner","repo").first()

        advanceUntilIdle()

        TestCase.assertEquals(ApiResult.Error(code = null, errorMessage = "No data found" , ErrorStatus.EMPTY_DATA), result)
    }

    @Test
    fun `getRepositoryDetails from api when network is not connected then should return error`() = runTest {

        coEvery { connectivityChecker.isConnected()} returns false

        val result = SUT.getRepositoryDetails("owner","repo").first()

        advanceUntilIdle()

        val expected = ApiResult.Error(code=null, errorMessage="No Internet Connection", errorStatus= ErrorStatus.No_INTERNET_CONNECTION)

        TestCase.assertEquals(expected, result)
    }
}