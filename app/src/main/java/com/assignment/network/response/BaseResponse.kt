package com.assignment.network.response

import androidx.lifecycle.MutableLiveData
import com.assignment.data.About
import java.lang.reflect.Constructor

data class BaseResponse(
    val title: String?,
    val aboutList: List<About>
) {
    constructor() : this("", emptyList())
}
