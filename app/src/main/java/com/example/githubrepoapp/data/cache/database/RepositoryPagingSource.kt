package com.example.githubrepoapp.data.cache.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubrepoapp.data.cache.database.dao.RepositoryDao
import com.example.githubrepoapp.data.cache.mappers.RepositoriesCacheMapper
import com.example.githubrepoapp.data.network.models.RepositoryModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class RepositoryPagingSource @Inject constructor(
    private val repositoryDao: RepositoryDao,
    private val repositoriesCacheMapper: RepositoriesCacheMapper
) : PagingSource<Int, RepositoryModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        try {

            val nextPageNumber = params.key ?: 1
            val pageSize = if (nextPageNumber == 1){ 10 }else { params.loadSize }
            val offset =  (nextPageNumber - 1) * pageSize
            val adjustedOffset = (offset / 10) * 10

            println("nextPageNumber: $nextPageNumber, pageSize: $pageSize, offset: $offset, adjustedOffset: $adjustedOffset")

            val prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null

            val data = repositoryDao.getAllRepo(pageSize, adjustedOffset)
                ?.map { repositoriesCacheMapper.mapFromEntity(it) }

            delay(2000)

            return LoadResult.Page(
                data = data!!,
                prevKey = prevKey,
                nextKey = if (data.isNotEmpty()) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id
        }
    }
}
