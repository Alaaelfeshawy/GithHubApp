package com.example.githubrepoapp.domain

import androidx.paging.Pager
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.repo.network.home.IHomeRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.home.GetRepositoryListUseCase
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetRepositoryListUseCaseTest : BaseUnitTest() {

    @MockK
    private lateinit var homeRepoImpl : IHomeRepo


    private lateinit var SUT : GetRepositoryListUseCase


    private val mockPager: Pager<Int, RepositoryModel> = mockk()

    @Before
    fun before(){
        SUT = GetRepositoryListUseCase(homeRepoImpl)
    }

    @Test
    fun `run function should return success with data`() = runTest {
        coEvery { homeRepoImpl.getRepositoryList() } returns flow {
            emit(ApiResult.Success(mockPager))
        }

        val result = SUT.run()

        assertEquals(ApiResult.Success(mockPager), result.first())
    }

    @Test
    fun `run function should return error`() = runTest {
        coEvery { homeRepoImpl.getRepositoryList() } returns flow {
            emit(ApiResult.Error(1, errorMessage ))
        }

        val result = SUT.run()

        assertEquals(ApiResult.Error(1, errorMessage), result.first())
    }
}