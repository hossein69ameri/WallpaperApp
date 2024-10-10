package academy.nouri.wallpaperapp.data.repository

import academy.nouri.wallpaperapp.data.network.ApiServices
import academy.nouri.wallpaperapp.ui.search.SearchPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiServices) {

    fun searchPhotos(query: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { SearchPagingSource(api, query) }
    ).liveData
}