package academy.nouri.wallpaperapp.data.repository

import academy.nouri.wallpaperapp.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getDetailPhoto(id: String) = api.getDetailPhoto(id)
}