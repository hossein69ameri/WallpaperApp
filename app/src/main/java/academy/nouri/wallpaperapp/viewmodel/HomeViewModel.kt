package academy.nouri.wallpaperapp.viewmodel

import academy.nouri.wallpaperapp.data.model.home.ResponseCategories
import academy.nouri.wallpaperapp.data.model.home.ResponsePhotos
import academy.nouri.wallpaperapp.data.repository.HomeRepository
import academy.nouri.wallpaperapp.utils.network.NetworkRequest
import academy.nouri.wallpaperapp.utils.network.NetworkResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            delay(300)
            callNewestPhotosApi()
            callCategoriesApi()
        }
    }

    //Newest
    private val _newestPhotosData = MutableLiveData<NetworkRequest<ResponsePhotos>>()
    val newestPhotosData: LiveData<NetworkRequest<ResponsePhotos>> = _newestPhotosData

    private fun callNewestPhotosApi() = viewModelScope.launch {
        _newestPhotosData.value = NetworkRequest.Loading()
        val response = repository.getNewestPhotos()
        _newestPhotosData.value = NetworkResponse(response).generateResponse()
    }

    //Color tone
    fun getColorToneList() = repository.fillColorTonesData()

    //Categories
    private val _categoriesData = MutableLiveData<NetworkRequest<ResponseCategories>>()
    val categoriesData: LiveData<NetworkRequest<ResponseCategories>> = _categoriesData

    private fun callCategoriesApi() = viewModelScope.launch {
        _categoriesData.value = NetworkRequest.Loading()
        val response = repository.getCategoriesList()
        _categoriesData.value = NetworkResponse(response).generateResponse()
    }
}