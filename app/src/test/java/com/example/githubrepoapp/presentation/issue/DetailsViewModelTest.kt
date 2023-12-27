package com.example.githubrepoapp.presentation.issue

import com.example.githubrepoapp.data.network.ApiResult
import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.domain.issue.GetIssuesUseCase
import com.example.githubrepoapp.presentation.issues.IssuesViewModel
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class IssuesViewModelTest : BaseUnitTest() {

    private lateinit var SUT : IssuesViewModel

    @MockK
    private lateinit var useCase : GetIssuesUseCase

    @MockK
    private lateinit var mockSuccessResponse : List<IssueModel>


    private val param = GetIssuesUseCase.Params("owner","repo")

    @Before
    fun before(){
        SUT = IssuesViewModel(useCase , dispatcher = testDispatcher)
    }

    @Test
    fun `should getIssues from useCase then return success state with data`() = runTest {
         every { useCase.execute(param) } returns flow {
            emit(ApiResult.Success(mockSuccessResponse))
        }

         SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( mockSuccessResponse , SUT.state.value.data)
    }

    @Test
    fun `should getIssues from useCase then return success state with empty data`() = runTest {
         every { useCase.execute(param) } returns flow {
            emit(ApiResult.Success(null))
        }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( null , SUT.state.value.data)
    }

    @Test
    fun `should getIssues from useCase then return error state `() = runTest {
         every { useCase.execute(param) } returns flow {
            emit(ApiResult.GenericError(1,errorMessage))
        }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( errorMessage , SUT.state.value.errorMessage)
    }

    @Test
    fun `should getIssues from useCase then return network error state `() = runTest {
        every { useCase.execute(param)} returns flow {
            emit(ApiResult.NetworkError(networkError))
        }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( networkError , SUT.state.value.errorMessage)
    }

    @Test
    fun `should getIssues from useCase then loading appears `() = runTest {
        every {useCase.execute(param) } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

        SUT.getIssues("owner","repo")

        assertEquals( true , SUT.state.value.isLoading)
    }

    @Test
    fun `should getIssues from useCase after getting data loading disappears `() = runTest {
        every {useCase.execute(param) } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( false , SUT.state.value.isLoading)
    }

}