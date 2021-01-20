package com.assignment.network

import com.assignment.util.AppConstants
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {

    /**
     * Get the list of the data from the API
     */
    @GET(AppConstants.SUB_URL)
    fun getContent(): Call<JsonElement>
}