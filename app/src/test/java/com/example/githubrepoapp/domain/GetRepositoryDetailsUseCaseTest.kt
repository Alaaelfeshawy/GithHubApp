package com.example.githubrepoapp.domain

import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.repo.network.details.IRepositoryDetailsRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.detail.GetRepositoryDetailsUseCase
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

class GetRepositoryDetailsUseCaseTest : BaseUnitTest() {

    @MockK
    private lateinit var repositoryDetailsRepo : IRepositoryDetailsRepo


    private lateinit var SUT : GetRepositoryDetailsUseCase


    private val mockResponse: RepositoryDetailsModel  = mockk()

    @Before
    fun before(){
        SUT = GetRepositoryDetailsUseCase(repositoryDetailsRepo)
    }

    @Test
    fun `run function should return success with data`() = runTest {
        coEvery { repositoryDetailsRepo.getRepositoryDetails("owner","repo") } returns flow {
            emit(ApiResult.Success(mockResponse))
        }

        val result = SUT.run(GetRepositoryDetailsUseCase.Params("owner","repo"))

        assertEquals(ApiResult.Success(mockResponse), result.first())
    }

    @Test
    fun `run function should return error`() = runTest {
        coEvery { repositoryDetailsRepo.getRepositoryDetails("owner","repo") } returns flow {
            emit(ApiResult.Error(1, errorMessage ))
        }

        val result = SUT.run(GetRepositoryDetailsUseCase.Params("owner","repo"))

        assertEquals(ApiResult.Error(1, errorMessage), result.first())
    }
}