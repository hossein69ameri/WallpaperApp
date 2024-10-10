package academy.nouri.wallpaperapp.viewmodel

import academy.nouri.wallpaperapp.data.model.splash.ResponseRandom
import academy.nouri.wallpaperapp.data.repository.SplashRepository
import academy.nouri.wallpaperapp.utils.network.NetworkRequest
import academy.nouri.wallpaperapp.utils.network.NetworkResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: SplashRepository) : ViewModel() {
    //Random
    private val _randomData = MutableLiveData<NetworkRequest<ResponseRandom>>()
    val randomData: LiveData<NetworkRequest<ResponseRandom>> = _randomData

    fun callRandomApi() = viewModelScope.launch {
        _randomData.value = NetworkRequest.Loading()
        try {
            val response = repository.getRandomPhoto()
            _randomData.value = NetworkResponse(response).generateResponse()
        } catch (e: IOException) {
            _randomData.value = NetworkRequest.Error("لطفا برای استفاده از برنامه از فیلترشکن استفاده کنید.")
        } catch (e: Exception) {
            _randomData.value = NetworkRequest.Error("خطا: ${e.localizedMessage}")
        }
    }
}