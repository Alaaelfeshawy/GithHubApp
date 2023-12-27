package com.example.githubrepoapp.presentation.home

import androidx.compose.runtime.collectAsState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.domain.home.GetRepositoryListUseCase
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseUnitTest() {

    private lateinit var mockGetRepositoryListUseCase: GetRepositoryListUseCase
    private lateinit var viewModel: HomeViewModel
    @MockK
    private lateinit var mockResponse : List<RepositoryModel>

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        mockGetRepositoryListUseCase = mockk()
        coEvery { mockGetRepositoryListUseCase.getReposSizeInDB() } returns 0
        coEvery { mockGetRepositoryListUseCase.run() } returns flowOf(ApiResult.Success(true))
        coEvery { mockGetRepositoryListUseCase.getReposFromDb() } returns flow { delay(1000) }

        viewModel = HomeViewModel(mockGetRepositoryListUseCase, testDispatcher)
    }

    @Test
    fun `getRepo() calls getReposFromDb() when size is greater than 0`() = runTest {
        // Arrange
        coEvery { mockGetRepositoryListUseCase.getReposSizeInDB() } returns 1

        // Act
        viewModel.getRepo()

        advanceUntilIdle()

        // Assert
        verify { mockGetRepositoryListUseCase.getReposFromDb() }
    }

    @Test
    fun `getRepo() calls getReposFromDb() when size is greater than 0 then return list of repo`() = runTest {
        // Arrange
        coEvery { mockGetRepositoryListUseCase.getReposSizeInDB() } returns 1

        coEvery { mockGetRepositoryListUseCase.getReposFromDb() } returns flowOf(PagingData.from(mockResponse))
        // Act
        viewModel.getRepo()

        advanceUntilIdle()
        // Assert
        verify { mockGetRepositoryListUseCase.getReposFromDb() }

        assertEquals(flowOf(PagingData.from(mockResponse)).first(),viewModel.state.value.data?.first())
    }

    @Test
    fun `getRepo() calls getRepositories() when size is 0`() = runTest {
        // Arrange
        coEvery { mockGetRepositoryListUseCase.getReposSizeInDB() } returns 0

        // Act
        viewModel.getRepo()

        advanceUntilIdle()

        // Assert
        verify { mockGetRepositoryListUseCase.run() }
    }

    @Test
    fun `getRepositories() sets error state on ApiResult Error`() = runTest {
        coEvery { mockGetRepositoryListUseCase.getReposSizeInDB() } returns 0

        // Arrange
        coEvery { mockGetRepositoryListUseCase.run() } returns flowOf(ApiResult.Error(1 , "No internet" , ErrorStatus.NO_CONNECTION))

        // Act
        viewModel.getRepo()

        advanceUntilIdle()
        // Assert
        assertEquals("No internet", viewModel.state.value.errorMessage)
    }

    @Test
    fun `getRepositories() calls getReposFromDb() on ApiResult Success`() = runTest {
        coEvery { mockGetRepositoryListUseCase.getReposSizeInDB() } returns 0

        // Arrange
        coEvery { mockGetRepositoryListUseCase.run() } returns flowOf(ApiResult.Success(true))

        // Act
        viewModel.getRepo()

        advanceUntilIdle()

        // Assert
        verify { mockGetRepositoryListUseCase.getReposFromDb() }
    }

//    @Test
//    fun `getReposFromDb() sets state with cachedIn PagingData`() = runTest {
//        // Arrange
//        coEvery { mockGetRepositoryListUseCase.getReposFromDb() } returns flowOf(PagingData.empty())
//
//        // Act
//        viewModel.getRepo()
//
//        advanceUntilIdle()
//
//        // Assert
//        assertEquals(viewModel.state.value, HomeState(isLoading = false, data = PagingData.empty()))
//    }
}
