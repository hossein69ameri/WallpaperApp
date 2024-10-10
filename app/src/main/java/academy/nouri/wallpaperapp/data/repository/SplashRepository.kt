package academy.nouri.wallpaperapp.data.repository

import academy.nouri.wallpaperapp.data.network.ApiServices
import javax.inject.Inject

class SplashRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getRandomPhoto() = api.getRandomPhoto()
}