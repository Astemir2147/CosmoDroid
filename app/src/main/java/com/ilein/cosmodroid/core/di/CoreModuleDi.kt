package com.ilein.cosmodroid.core.di

import com.ilein.cosmodroid.core.api.AddLoggingInterceptor
import com.ilein.cosmodroid.core.api.ApiRequest
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module{
    single<ApiRequest> {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://ll.thespacedevs.com/2.2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(AddLoggingInterceptor.setLogging())
                .build()
        retrofit.create(ApiRequest::class.java)
    }
}