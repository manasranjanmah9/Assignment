package com.assignment.network

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("s/2iodh4vg0eortkl/facts.json")
    abstract fun getContent(): Call<JsonElement>
}