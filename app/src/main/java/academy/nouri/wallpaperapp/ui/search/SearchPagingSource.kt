package academy.nouri.wallpaperapp.ui.search

import academy.nouri.wallpaperapp.data.model.search.ResponseSearch.Result
import academy.nouri.wallpaperapp.data.network.ApiServices
import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class SearchPagingSource @Inject constructor(private val api: ApiServices, private val query: String) :
    PagingSource<Int, Result>() {

    private val pageIndex = 1

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: pageIndex
        return try {
            val response = api.getSearchPhotos(query, position)
            val data = response.body()?.results!!

            LoadResult.Page(
                data = data,
                prevKey = if (position == pageIndex) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}