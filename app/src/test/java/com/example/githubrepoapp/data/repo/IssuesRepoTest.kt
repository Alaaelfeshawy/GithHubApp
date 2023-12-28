package com.example.githubrepoapp.data.repo

import com.example.githubrepoapp.data.network.apis.IssuesApi
import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.data.repo.network.ConnectivityChecker
import com.example.githubrepoapp.data.repo.network.issues.IssuesRepoImpl
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

class IssuesRepoTest : BaseUnitTest() {
    private val issuesApi: IssuesApi = mockk()
    private val connectivityChecker: ConnectivityChecker = mockk()
    private val mockResponse= mockk<List<IssueModel>>()
    private lateinit var SUT: IssuesRepoImpl

    @Before
    fun before(){
        SUT = IssuesRepoImpl(issuesApi, connectivityChecker)
    }

    @Test
    fun `getIssues from api when network is connected then should return success with data`() = runTest {
        coEvery { issuesApi.getIssuesList("owner","repo")} returns Response.success(mockResponse)

        coEvery { connectivityChecker.isConnected()} returns true

        val result = SUT.getIssues("owner","repo").first()

        advanceUntilIdle()

        TestCase.assertEquals(ApiResult.Success(mockResponse), result)
    }

    @Test
    fun `getRepositoryDetails from api when and network is connected then should return success with no data`() = runTest {
        coEvery { issuesApi.getIssuesList("owner","repo")} returns Response.success(null)

        coEvery { connectivityChecker.isConnected()} returns true


        val result = SUT.getIssues("owner","repo").first()

        advanceUntilIdle()

        TestCase.assertEquals(ApiResult.Error(code = null, errorMessage = "No data found" , ErrorStatus.EMPTY_DATA), result)
    }

    @Test
    fun `getRepositoryDetails from api when network is not connected then should return error`() = runTest {

        coEvery { connectivityChecker.isConnected()} returns false

        val result = SUT.getIssues("owner","repo").first()

        advanceUntilIdle()

        val expected = ApiResult.Error(code=null, errorMessage="No Internet Connection", errorStatus= ErrorStatus.No_INTERNET_CONNECTION)

        TestCase.assertEquals(expected, result)
    }
}