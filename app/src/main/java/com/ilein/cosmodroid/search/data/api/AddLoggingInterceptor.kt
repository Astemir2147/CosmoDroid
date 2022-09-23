package com.ilein.cosmodroid.search.data.api

import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor

object AddLoggingInterceptor {
    fun setLogging(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply { loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}