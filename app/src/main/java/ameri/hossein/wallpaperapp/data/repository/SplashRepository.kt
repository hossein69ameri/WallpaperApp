package ameri.hossein.wallpaperapp.data.repository

import ameri.hossein.wallpaperapp.data.network.ApiServices
import javax.inject.Inject

class SplashRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getRandomPhoto() = api.getRandomPhoto()
}