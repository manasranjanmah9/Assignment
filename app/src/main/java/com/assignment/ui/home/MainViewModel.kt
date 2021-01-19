package com.assignment.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.network.NetworkRepository
import com.assignment.network.response.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    private val response = MutableLiveData<BaseResponse>()
    private val _isLoading = MutableLiveData(true)

    fun getData(): LiveData<BaseResponse> {
        return networkRepository.getBaseResponse(_isLoading)
    }

    val isLoading: LiveData<Boolean> = _isLoading

    init {
//        fetchData()
    }

    public fun swipeRefresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val baseResponse = networkRepository.getBaseResponse(_isLoading)
            Log.e("TAG==>", "fetchData: ${baseResponse}" )
        }
    }

}