package com.example.githubrepoapp.presentation.issue

import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.domain.issue.GetIssuesUseCase
import com.example.githubrepoapp.presentation.base.ErrorNetwork
import com.example.githubrepoapp.presentation.base.ErrorToast
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
         every { useCase.run(param) } returns flow {
            emit(ApiResult.Success(mockSuccessResponse))
        }

         SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( mockSuccessResponse , SUT.state.value.data)
    }

    @Test
    fun `should getIssues from useCase then return success state with empty data`() = runTest {
         every { useCase.run(param) } returns flow {
            emit(ApiResult.Success(null))
        }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( null , SUT.state.value.data)
    }

    @Test
    fun `should getIssues from useCase then return error state `() = runTest {
         every { useCase.run(param) } returns flow {
            emit(ApiResult.Error(1,errorMessage , ErrorStatus.ERROR))
        }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( errorMessage , (SUT.state.value.errorView as ErrorToast).errorMessage)
    }

    @Test
    fun `should getIssues from useCase then return network error state `() = runTest {
        every { useCase.run(param)} returns flow {
            emit(ApiResult.Error(code = null , networkError , ErrorStatus.No_INTERNET_CONNECTION))
        }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals(networkError , (SUT.state.value.errorView as ErrorNetwork).errorMessage)
    }

    @Test
    fun `should getIssues from useCase then loading appears `() = runTest {
        every {useCase.run(param) } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

        SUT.getIssues("owner","repo")

        assertEquals( true , SUT.state.value.isLoading)
    }

    @Test
    fun `should getIssues from useCase after getting data loading disappears `() = runTest {
        every {useCase.run(param) } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

        SUT.getIssues("owner","repo")

        advanceUntilIdle()

        assertEquals( false , SUT.state.value.isLoading)
    }

}