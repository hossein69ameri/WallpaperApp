package ameri.hossein.wallpaperapp.viewmodel

import ameri.hossein.wallpaperapp.data.model.detail.ResponseDetail
import ameri.hossein.wallpaperapp.data.repository.DetailRepository
import ameri.hossein.wallpaperapp.utils.network.NetworkRequest
import ameri.hossein.wallpaperapp.utils.network.NetworkResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {
    //Random
    private val _detailData = MutableLiveData<NetworkRequest<ResponseDetail>>()
    val detailData: LiveData<NetworkRequest<ResponseDetail>> = _detailData

    fun callDetailApi(id: String) = viewModelScope.launch {
        _detailData.value = NetworkRequest.Loading()
        val response = repository.getDetailPhoto(id)
        _detailData.value = NetworkResponse(response).generateResponse()
    }
}