package com.example.githubrepoapp.presentation.home

import com.example.githubrepoapp.data.network.ApiResult
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.domain.home.GetRepositoryListUseCase
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeViewModelTest : BaseUnitTest() {

    private lateinit var SUT : HomeViewModel

    @MockK
    private lateinit var useCase : GetRepositoryListUseCase

    @MockK
    private lateinit var mockSuccessResponse : List<RepositoryModel>

    @Before
    fun before(){
        SUT = HomeViewModel(useCase , dispatcher = testDispatcher)
    }

    @Test
    fun `should getData from useCase then return success state with data`() = runTest {
         every { useCase.execute() } returns flow {
            emit(ApiResult.Success(mockSuccessResponse))
        }

         SUT.getRepositories()

        advanceUntilIdle()

        assertEquals( mockSuccessResponse , SUT.state.value.data)
    }

    @Test
    fun `should getData from useCase then return success state with empty data`() = runTest {
         every { useCase.execute() } returns flow {
            emit(ApiResult.Success(emptyList()))
        }

         SUT.getRepositories()

        advanceUntilIdle()

        assertEquals( listOf<RepositoryModel>() , SUT.state.value.data)
    }

    @Test
    fun `should getData from useCase then return error state `() = runTest {
         every { useCase.execute() } returns flow {
            emit(ApiResult.GenericError(1,errorMessage))
        }

         SUT.getRepositories()

        advanceUntilIdle()

        assertEquals( errorMessage , SUT.state.value.errorMessage)
    }

    @Test
    fun `should getData from useCase then return network error state `() = runTest {
         every { useCase.execute() } returns flow {
            emit(ApiResult.NetworkError(networkError))
        }

         SUT.getRepositories()

        advanceUntilIdle()

        assertEquals( networkError , SUT.state.value.errorMessage)
    }

    @Test
    fun `should getData from useCase then loading appears `() = runTest {
        every { useCase.execute() } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

         SUT.getRepositories()

        assertEquals( true , SUT.state.value.isLoading)
    }

    @Test
    fun `should getData from useCase after getting data loading disappears `() = runTest {
        every { useCase.execute() } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

         SUT.getRepositories()

        advanceUntilIdle()

        assertEquals( false , SUT.state.value.isLoading)
    }

}