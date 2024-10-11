package ameri.hossein.wallpaperapp.data.repository

import ameri.hossein.wallpaperapp.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getDetailPhoto(id: String) = api.getDetailPhoto(id)
}