package com.ilein.cosmodroid.search.data.api

import com.ilein.cosmodroid.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://${BuildConfig.API_BASE_URL}/${BuildConfig.API_VERSION}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiRequest: ApiRequest by lazy {
        retrofit.create(ApiRequest::class.java)
    }

}