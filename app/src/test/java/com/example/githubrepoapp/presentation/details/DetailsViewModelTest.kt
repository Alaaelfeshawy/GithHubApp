package com.example.githubrepoapp.presentation.details

import com.example.githubrepoapp.data.network.ApiResult
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.domain.detail.GetRepositoryDetailsUseCase
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

class DetailsViewModelTest : BaseUnitTest() {

    private lateinit var SUT : DetailsViewModel

    @MockK
    private lateinit var useCase : GetRepositoryDetailsUseCase

    @MockK
    private lateinit var mockSuccessResponse : RepositoryDetailsModel


    private val param = GetRepositoryDetailsUseCase.Params("owner","repo")

    @Before
    fun before(){
        SUT = DetailsViewModel(useCase , dispatcher = testDispatcher)
    }

    @Test
    fun `should getRepositoryDetails from useCase then return success state with data`() = runTest {
         every { useCase.execute(param) } returns flow {
            emit(ApiResult.Success(mockSuccessResponse))
        }

         SUT.getRepositoryDetails("owner","repo")

        advanceUntilIdle()

        assertEquals( mockSuccessResponse , SUT.state.value.data)
    }

    @Test
    fun `should getRepositoryDetails from useCase then return success state with empty data`() = runTest {
         every { useCase.execute(param) } returns flow {
            emit(ApiResult.Success(null))
        }

        SUT.getRepositoryDetails("owner","repo")

        advanceUntilIdle()

        assertEquals( null , SUT.state.value.data)
    }

    @Test
    fun `should getRepositoryDetails from useCase then return error state `() = runTest {
         every { useCase.execute(param) } returns flow {
            emit(ApiResult.GenericError(1,errorMessage))
        }

        SUT.getRepositoryDetails("owner","repo")

        advanceUntilIdle()

        assertEquals( errorMessage , SUT.state.value.errorMessage)
    }

    @Test
    fun `should getRepositoryDetails from useCase then return network error state `() = runTest {
        every { useCase.execute(param)} returns flow {
            emit(ApiResult.NetworkError(networkError))
        }

        SUT.getRepositoryDetails("owner","repo")

        advanceUntilIdle()

        assertEquals( networkError , SUT.state.value.errorMessage)
    }

    @Test
    fun `should getRepositoryDetails from useCase then loading appears `() = runTest {
        every {useCase.execute(param) } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

        SUT.getRepositoryDetails("owner","repo")

        assertEquals( true , SUT.state.value.isLoading)
    }

    @Test
    fun `should getRepositoryDetails from useCase after getting data loading disappears `() = runTest {
        every {useCase.execute(param) } returns flow { emit(ApiResult.Success(mockSuccessResponse)) }

        SUT.getRepositoryDetails("owner","repo")

        advanceUntilIdle()

        assertEquals( false , SUT.state.value.isLoading)
    }

}