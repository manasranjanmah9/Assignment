package com.assignment.ui.home.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.data.About
import com.assignment.network.NetworkRepository
import com.assignment.network.response.BaseResponse

class MainViewModel(networkRepository: NetworkRepository) : ViewModel() {

    private var response : LiveData<BaseResponse>
    private val _isLoading = MutableLiveData(true)
    private var allData: LiveData<List<About>>

    val isLoading: LiveData<Boolean> = _isLoading

    fun getData(): LiveData<BaseResponse> {
        return response
    }
    fun getAboutListDataFromRoomDb(): LiveData<List<About>> {
        return allData
    }

    init {
        response = networkRepository.getBaseResponse(_isLoading)
        allData = networkRepository.getAboutListData()
    }

}