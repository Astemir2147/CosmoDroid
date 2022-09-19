package com.ilein.cosmodroid.feature_news_list.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://ll.thespacedevs.com/2.2.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}