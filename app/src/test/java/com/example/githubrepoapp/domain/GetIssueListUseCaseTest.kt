package com.example.githubrepoapp.domain

import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.data.repo.network.issues.IIssuesRepo
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.domain.issue.GetIssuesUseCase
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

class GetIssueListUseCaseTest : BaseUnitTest() {

    @MockK
    private lateinit var issuesRepo : IIssuesRepo


    private lateinit var SUT : GetIssuesUseCase


    private val mockResponse: List<IssueModel> = mockk()

    @Before
    fun before(){
        SUT = GetIssuesUseCase(issuesRepo)
    }

    @Test
    fun `run function should return success with data`() = runTest {
        coEvery { issuesRepo.getIssues("owner","repo") } returns flow {
            emit(ApiResult.Success(mockResponse))
        }

        val result = SUT.run(GetIssuesUseCase.Params("owner","repo"))

        assertEquals(ApiResult.Success(mockResponse), result.first())
    }

    @Test
    fun `run function should return error`() = runTest {
        coEvery { issuesRepo.getIssues("owner","repo") } returns flow {
            emit(ApiResult.Error(1, errorMessage ))
        }

        val result = SUT.run(GetIssuesUseCase.Params("owner","repo"))

        assertEquals(ApiResult.Error(1, errorMessage), result.first())
    }
}