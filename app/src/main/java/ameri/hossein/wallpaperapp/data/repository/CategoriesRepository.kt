package ameri.hossein.wallpaperapp.data.repository

import ameri.hossein.wallpaperapp.data.network.ApiServices
import ameri.hossein.wallpaperapp.ui.categories.CategoriesPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val api: ApiServices) {

    fun categoryPhotos(id: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { CategoriesPagingSource(api, id) }
    ).liveData
}