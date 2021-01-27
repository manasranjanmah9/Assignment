package com.assignment.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.data.About
import com.assignment.data.db.AppDatabase
import com.assignment.data.db.entities.AboutDao
import com.assignment.network.response.BaseResponse
import com.assignment.util.AppConstants
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

class NetworkRepository(appDatabase: AppDatabase) {

    private var aboutDao: AboutDao = appDatabase.getAboutDao()
    private var allData: LiveData<List<About>>

    init {
        allData = aboutDao.getAboutList()
    }

    fun getAboutListData(): LiveData<List<About>> = allData

    /*Getting 'OkHttpClient'*/
    private fun getOKHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        return httpClient
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    fun getBaseResponse(
        _isLoading: MutableLiveData<Boolean>
    ): LiveData<BaseResponse> {
        val data = MutableLiveData<BaseResponse>()
        var baseResponse: BaseResponse
        try {
            _isLoading.value = true
            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
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
            _isLoading.value = false
        }

        return data
    }

    /*
    * parsing string to BaseResponse
    * */
    private fun parseBaseResponse(response: String): BaseResponse {
        val mainTitle = JSONObject(response).optString("title")
        val dataArray = JSONObject(response).optJSONArray("rows")

        val list = (
                GsonBuilder().create()
                    .fromJson(dataArray?.toString(), Array<About>::class.java)
                    .toList())
        Thread {
            aboutDao.deleteAllData()
            aboutDao.insertData(list)
        }.start()
        return BaseResponse(
            mainTitle, list
        )
    }


}