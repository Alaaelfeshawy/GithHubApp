package com.example.githubrepoapp.presentation.home

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.utils.ApiResult
import com.example.githubrepoapp.data.utils.ErrorStatus
import com.example.githubrepoapp.domain.detail.GetRepositoryDetailsUseCase
import com.example.githubrepoapp.domain.home.GetRepositoryListUseCase
import com.example.githubrepoapp.domain.issue.GetIssuesUseCase
import com.example.githubrepoapp.presentation.base.ErrorToast
import com.example.githubrepoapp.presentation.details.DetailsViewModel
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeViewModelTest : BaseUnitTest(){

    private lateinit var useCase : GetRepositoryListUseCase

    private lateinit var SUT : HomeViewModel

    private val mockPager: Pager<Int, RepositoryModel> = mockk()

    private val mockPaging: PagingData<RepositoryModel> = mockk()

    @Before
    fun before(){
        useCase =  mockk()
        SUT = HomeViewModel(useCase , testDispatcher)
    }

    @Test
    fun `getRepositories() should loading then after getting success data appears data and loading disappears`() = runTest {

        every { mockPager.flow } returns flow {
            emit(mockPaging)
        }

        every { useCase.run() } returns flow {
            emit(ApiResult.Success(mockPager))
        }


        SUT.getRepositories()

        assertEquals(true,SUT.state.value.isLoading)

        advanceUntilIdle()

        assertEquals(false,SUT.state.value.isLoading)

        assertEquals(mockPaging,SUT.state.value.data?.first() )

    }

    @Test
    fun `getRepositories() should loading then after getting error appears error and loading disappears`() = runTest {
        val errorResult = ApiResult.Error(code = 1 , errorMessage = errorMessage , errorStatus = ErrorStatus.ERROR)

        coEvery { useCase.run() } returns flowOf(errorResult)

        SUT.getRepositories()

        assertEquals(true,SUT.state.value.isLoading)

        advanceUntilIdle()

        assertEquals(false,SUT.state.value.isLoading)

        assertEquals(errorMessage,(SUT.state.value.errorView as ErrorToast).errorMessage)

    }
}

