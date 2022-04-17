package com.example.android.testram.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.testram.data.network.TestRaMApiService
import com.example.android.testram.data.network.pojo.CharacterPojo
import retrofit2.HttpException

private const val STARTING_PAGE = 1

class SearchPagingSource(
    private val service: TestRaMApiService,
) : PagingSource<Int, CharacterPojo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterPojo> {
        try {
            val position = params.key ?: STARTING_PAGE
            val response = service.getListCharacters(page = position)

            return if (response.isSuccessful) {
                val searchResults = response.body()?.results ?: emptyList()
                val nextKey = if (searchResults.isEmpty()) null else position + 1
                val prevKey = if (position == STARTING_PAGE) null else position - 1
                LoadResult.Page(data = searchResults, prevKey = prevKey, nextKey = nextKey)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterPojo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
