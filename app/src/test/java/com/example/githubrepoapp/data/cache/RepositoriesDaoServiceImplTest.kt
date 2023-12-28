package com.example.githubrepoapp.data.cache

import com.example.githubrepoapp.data.cache.database.RepositoryPagingSource
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.cache.models.RepositoriesCacheEntity
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.data.repo.cache.repositories.RepositoriesDaoServiceImpl
import com.example.githubrepoapp.presentation.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoriesDaoServiceImplTest : BaseUnitTest() {

    private val repositoryDao: RepositoryDao = mockk()
    private val repositoriesCacheMapper: RepositoriesCacheMapper= mockk()
    private val repositoryPagingSource : RepositoryPagingSource = mockk()
    private val mockListModel = mockk<List<RepositoryModel>>()
    private val mockListEntity = mockk<List<RepositoriesCacheEntity>>()
    private lateinit var SUT: RepositoriesDaoServiceImpl

    @Before
    fun before(){
        SUT = RepositoriesDaoServiceImpl(repositoryDao, repositoriesCacheMapper,repositoryPagingSource)
    }

    @Test
    fun `insertRepos() list of repos in db then should return success`() = runTest {
        val expectedResponse = LongArray(1)

        every {repositoriesCacheMapper.reposListToEntityList(mockListModel)} returns mockListEntity

        coEvery {repositoryDao.insertAll(mockListEntity)} returns expectedResponse


        val result = SUT.insertRepos(mockListModel).size

        advanceUntilIdle()

        assertEquals(expectedResponse.size, result)
    }
    @Test
    fun `getReposSizeInDB() then should return all list size which inserted in db`() = runTest{

        every { repositoriesCacheMapper.entityListToRepositoriesList(mockListEntity) } returns mockListModel

        coEvery { repositoryDao.getAllRepo() } returns mockListEntity

        every { mockListModel.size } returns 1

        val result = SUT.getReposSizeInDB()

        assertEquals(mockListModel.size, result)
    }



}