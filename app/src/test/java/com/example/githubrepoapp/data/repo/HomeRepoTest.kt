package com.example.githubrepoapp.data.repo

import androidx.paging.Pager
import com.example.githubrepoapp.data.network.apis.HomeAPI
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoService
import com.example.githubrepoapp.data.repo.network.ConnectivityChecker
import com.example.githubrepoapp.data.repo.network.home.HomeRepoImpl
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

class HomeRepoTest : BaseUnitTest() {
    private val homeAPI: HomeAPI = mockk()
    private val homeDaoService: RepositoriesDaoService = mockk()
    private val connectivityChecker: ConnectivityChecker = mockk()
    private val mockList = mockk<List<RepositoryModel>>()
    private lateinit var SUT: HomeRepoImpl
    private val mockPager: Pager<Int, RepositoryModel> = mockk()

    @Before
    fun before(){
        SUT = HomeRepoImpl(homeAPI, homeDaoService, connectivityChecker)
    }

    @Test
    fun `getRepositories from api when database equals zero and network is connected then should return success with data`() = runTest {
        coEvery { homeAPI.getRepositoryList()} returns Response.success(mockList)

        coEvery { homeDaoService.getReposSizeInDB()} returns 0

        coEvery { homeDaoService.getRepositoryFromDB()} returns mockPager

        coEvery { connectivityChecker.isConnected()} returns true

        coEvery { homeDaoService.insertRepos(mockList) } returns LongArray(1)

        val result = SUT.getRepositoryList().first()

        advanceUntilIdle()

        TestCase.assertEquals(ApiResult.Success(mockPager), result)
    }

    @Test
    fun `getRepositories from api when database equals zero and network is connected then should return success with no data`() = runTest {
        coEvery { homeAPI.getRepositoryList()} returns Response.success(null)

        coEvery { homeDaoService.getReposSizeInDB()} returns 0

        coEvery { homeDaoService.getRepositoryFromDB()} returns mockPager

        coEvery { connectivityChecker.isConnected()} returns true

        coEvery { homeDaoService.insertRepos(mockList) } returns LongArray(1)

        val result = SUT.getRepositoryList().first()

        advanceUntilIdle()

        println(result)
        TestCase.assertEquals(ApiResult.Error(code = null, errorMessage = "No data found" , ErrorStatus.EMPTY_DATA), result)
    }

    @Test
    fun `getRepositories from database equals zero should return success with data`() = runTest {

        coEvery { homeDaoService.getReposSizeInDB()} returns 1

        coEvery { homeDaoService.getRepositoryFromDB()} returns mockPager

        val result = SUT.getRepositoryList().first()

        advanceUntilIdle()

        TestCase.assertEquals(ApiResult.Success(mockPager), result)
    }

    @Test
    fun `getRepositories from api when database equals zero and network is not connected then should return error`() = runTest {

        coEvery { homeDaoService.getReposSizeInDB()} returns 0

        coEvery { connectivityChecker.isConnected()} returns false

        val result = SUT.getRepositoryList().first()

        advanceUntilIdle()

        val expected = ApiResult.Error(code=null, errorMessage="No Internet Connection", errorStatus= ErrorStatus.No_INTERNET_CONNECTION)

        TestCase.assertEquals(expected, result)
    }
}