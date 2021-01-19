package com.assignment.network.response

import androidx.lifecycle.MutableLiveData
import com.assignment.data.About

data class BaseResponse(
    val title: String?,
    val aboutList: List<About>/*MutableLiveData<List<About>>*/
)
