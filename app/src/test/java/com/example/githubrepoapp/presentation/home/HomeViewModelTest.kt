package com.example.githubrepoapp.presentation.home

import androidx.paging.Pager
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.domain.home.GetRepositoryListUseCase
import com.example.githubrepoapp.domain.issue.GetIssuesUseCase
import com.example.githubrepoapp.presentation.base.ErrorToast
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeViewModelTest : BaseUnitTest(){

    @MockK
    private lateinit var useCase : GetRepositoryListUseCase


    private lateinit var SUT : HomeViewModel

    @MockK
    private lateinit var mockResponse : List<RepositoryModel>

    val pagingData: Pager<Int, RepositoryModel> = mockk()

    @Before
    fun before(){
        SUT = HomeViewModel(useCase , testDispatcher)
    }

    @Test
    fun `getRepositories success`() = runTest {
        // Given
        // Stub the getRepositoryListUseCase to return a flow
        coEvery { useCase.run() } returns flowOf(ApiResult.Success(pagingData))

        // When
        SUT.getRepositories()

        // Then
        // Ensure loading state is emitted
        assertTrue(SUT.state.value.isLoading!!)

        // Advance the time to ensure that the coroutine runs
        advanceUntilIdle()

        // Ensure loading state is not emitted anymore
        assertFalse(SUT.state.value.isLoading!!)

        // Ensure data state is emitted with the expected result
        assertEquals(pagingData, SUT.state.value.data?.first())
    }

    @Test
    fun `getRepositories error`() = runTest {
        // Given
        val errorResult = ApiResult.Error(code = 1 , errorMessage = errorMessage , errorStatus = ErrorStatus.ERROR)

        // Stub the getRepositoryListUseCase to return an error
        coEvery { useCase.run() } returns flowOf(errorResult)

        // When
        SUT.getRepositories()

        // Then
        // Ensure loading state is emitted
        assertEquals(true,SUT.state.value.isLoading)

        // Advance the time to ensure that the coroutine runs
        advanceUntilIdle()

        // Ensure loading state is not emitted anymore
        assertEquals(false,SUT.state.value.isLoading)

        // Ensure error state is emitted with the expected message
        assertEquals(errorMessage,(SUT.state.value.errorView as ErrorToast).errorMessage)

    }
}

data class DataResult(val pagingData: Pager<Int, RepositoryModel>)