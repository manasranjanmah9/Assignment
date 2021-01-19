package com.assignment.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.data.About
import com.assignment.network.response.BaseResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class NetworkRepository {
    val FULL_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json"
    val BASE_URL = "https://dl.dropboxusercontent.com/"

    /*Getting 'OkHttpClient'*/
    private fun getOKHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        val okHttpClient = httpClient
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        return okHttpClient
    }


    fun getBaseResponse(_isLoading: MutableLiveData<Boolean>): LiveData<BaseResponse> {
        val data = MutableLiveData<BaseResponse>()
        var baseResponse: BaseResponse
        val response = ""

        try {
            _isLoading.value = true
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOKHttpClient())
                .build()

            val userListApi = retrofit.create(NetworkApi::class.java)

            userListApi.getContent().enqueue(object : Callback<JsonElement> {
                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    baseResponse = parseBaseResponse(response.body().toString())
                    data.value = baseResponse
                    _isLoading.value = false
                }

                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    _isLoading.value = false
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data
    }

    private fun parseBaseResponse(response: String): BaseResponse {
        val mainTitle = JSONObject(response).optString("title")
        val dataArray = JSONObject(response).optJSONArray("rows")
        return BaseResponse(
            mainTitle, (
                    GsonBuilder().create()
                        .fromJson(dataArray?.toString(), Array<About>::class.java)
                        .toList())
        )
    }


}